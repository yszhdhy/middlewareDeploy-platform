package com.example.middlewaredeploy.constant;

/**
 * @project middlewareDeploy
 * @description elasticsearch脚本
 * @author capture or new
 * @date 2023/7/24 16:09:58
 * @version 1.0
 */
public enum ElasticsearchShell implements Adapter {

    ELASTICSEARCH_SHELL("  elasticsearch:\n" +
            "    image: docker.elastic.co/elasticsearch/elasticsearch:7.14.0\n" +
            "    container_name: elasticsearch\n" +
            "    environment:\n" +
            "      - discovery.type=single-node\n" +
            "      - \"ES_JAVA_OPTS=-Xms512m -Xmx512m\"  # 设置Elasticsearch的内存限制，根据需要进行调整\n" +
            "    user: \"0:0\"\n"+
            "    ports:\n" +
            "      - \"9200:9200\"\n" +
            "      - \"9300:9300\"\n" +
            "    volumes:\n" +
            "      - ./es-data:/usr/share/elasticsearch/data "+
            "\n"),


    KIBANA_SHELL("  kibana:\n" +
            "    image: docker.elastic.co/kibana/kibana:7.14.0\n" +
            "    container_name: kibana\n" +
            "    ports:\n" +
            "      - \"5601:5601\"\n" +
            "    environment:\n" +
            "      - ELASTICSEARCH_HOSTS=http://elasticsearch:9200"+
            "\n"),

    ;
    private final String command;

    ElasticsearchShell(String command) {
        this.command = command;
    }
    @Override
    public String getCommand() {
        return command;
    }
}
