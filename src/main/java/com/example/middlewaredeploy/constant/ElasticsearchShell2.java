package com.example.middlewaredeploy.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @project middlewareDeploy
 * @description elasticsearch脚本
 * @author capture or new
 * @date 2023/7/24 16:09:58
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum ElasticsearchShell2 {

    ELASTICSEARCH_DISPOSITION(new HashMap<String,Object>(){{
        put("image","docker.elastic.co/elasticsearch/elasticsearch:7.14.0");
        put("container_name","elasticsearch");
        put("environment",new ArrayList<String>(){{
            add("discovery.type=single-node");
            add("ES_JAVA_OPTS=-Xms512m -Xmx512m");
        }});
        put("ports",new ArrayList<String>(){{
            add("9200:9200");
            add("9300:9300");
        }});
        put("volumes",new ArrayList<String>(){{
            add("esdata:/usr/share/elasticsearch/data");
        }});
    }},"services","elasticsearch"),


    VOLUMES(new HashMap<String,Object>(){{
           put("esdata",null);
    }},"volumes",null),
    ;

    private HashMap<String,Object> map;
    private String level;
    private String dispositionName;

}
