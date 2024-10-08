package com.skjun.log.server.store.lucene.handler;

import cn.hutool.core.date.DateTime;
import com.skjun.log.server.core.deal.LogDataDealHandler;
import com.skjun.log.server.core.dto.LogUpMessage;
import com.skjun.log.server.core.dto.TraceUpData;
import com.skjun.log.server.store.lucene.config.LuceneConfig;
import lombok.Synchronized;
import lombok.extern.log4j.Log4j2;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.NRTCachingDirectory;
import org.apache.lucene.util.BytesRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Service
@Log4j2
public class LuceneLogDataHandler implements LogDataDealHandler {

    @Autowired
    LuceneConfig luceneConfig;


    @Override
    public void init() {

    }

    @Override
    public void handler(TraceUpData traceUpData) {
        log.info("lucene save -------------"+traceUpData.getUpLogs().toString());

        Collection<Document> documents = this.convertToDoc(traceUpData);
        try {
            writeToLucene(documents,getStoreIndex());
            log.info("lucene save end -------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getStoreIndex(){
        return DateTime.of(System.currentTimeMillis()).toString("YYYYMMDD");
    }

    @Synchronized
    private void writeToLucene(Collection<Document> docs, String index) throws Exception {
        IndexWriter indexWriter=null;
        try {
            Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(luceneConfig.getStorePath()+index));
            NRTCachingDirectory nrtCachingDirectory = new NRTCachingDirectory(directory, 5, 60);
            SmartChineseAnalyzer smartChineseAnalyzer = new SmartChineseAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(smartChineseAnalyzer);
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            indexWriter = new IndexWriter(nrtCachingDirectory, indexWriterConfig);
            indexWriter.addDocuments(docs);
            indexWriter.forceMerge(1);
            indexWriter.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (indexWriter != null) {
                indexWriter.close();
            }
        }
    }


    private Collection<Document> convertToDoc(TraceUpData traceUpData) {
        Collection<Document> docs = new ArrayList<>();
        for (LogUpMessage rm : traceUpData.getUpLogs()) {
            Document document = new Document();
            document.add(new StringField("id", UUID.randomUUID().toString(), Field.Store.YES));

            document.add(new StringField("logLevel", (rm.getLogLevel() == null) ? "" : rm.getLogLevel(), Field.Store.YES));
            document.add(new SortedDocValuesField("logLevel", new BytesRef((rm.getLogLevel() == null) ? "" : rm.getLogLevel())));

            document.add(new TextField("content", (rm.getContent() == null) ? "" : rm.getContent(), Field.Store.YES));

            document.add(new StringField("threadName", (rm.getThreadName() == null) ? "" : rm.getThreadName(), Field.Store.YES));
            document.add(new StringField("methodName", (rm.getMethodName() == null) ? "" : rm.getMethodName(), Field.Store.YES));
            document.add(new StringField("className", (rm.getClassName() == null) ? "" : rm.getClassName(), Field.Store.YES));

            document.add(new NumericDocValuesField("time", rm.getCreateTime()));
            document.add(new StoredField("createTime", rm.getCreateTime()));
            document.add(new StringField("traceId", (rm.getTraceId() == null) ? "" : rm.getTraceId(), Field.Store.YES));
            docs.add(document);
        }
        return docs;
    }
}
