package com.example.middlewaredeploy.constant;

public enum RocketmqShell implements Adapter{
    ROCKETMQ_SHELL("  mall4cloud-rocketmq-namesrv:\n" +
            "    image: apache/rocketmq:4.9.4\n" +
            "    container_name: mall4cloud-rocketmq-namesrv\n" +
            "    restart: always\n" +
            "    ports:\n" +
            "      - 9876:9876\n" +
            "    volumes:\n" +
            "      - ./rocketmq/namesrv/logs:/home/rocketmq/logs\n" +
            "      - ./rocketmq/namesrv/store:/home/rocketmq/store\n" +
            "    environment:\n" +
            "      JAVA_OPT_EXT: \"-Duser.home=/home/rocketmq -Xms512M -Xmx512M -Xmn128M\"\n" +
            "    command: [\"sh\",\"mqnamesrv\"]\n" +
            "\n" +
            "  mall4cloud-rocketmq-broker:\n" +
            "    image: apache/rocketmq:4.9.4\n" +
            "    container_name: mall4cloud-rocketmq-broker\n" +
            "    restart: always\n" +
            "    ports:\n" +
            "      - 10909:10909\n" +
            "      - 10911:10911\n" +
            "    volumes:\n" +
            "      - ./rocketmq/broker/logs:/home/rocketmq/logs\n" +
            "      - ./rocketmq/broker/store:/home/rocketmq/store\n" +
            "      - ./rocketmq/broker/conf/broker.conf:/etc/rocketmq/broker.conf\n" +
            "    environment:\n" +
            "      JAVA_OPT_EXT: \"-Duser.home=/home/rocketmq -Xms512M -Xmx512M -Xmn128M -XX:-AssumeMP\"\n" +
            "    command: [\"sh\",\"mqbroker\",\"-c\",\"/etc/rocketmq/broker.conf\",\"-n\",\"mall4cloud-rocketmq-namesrv:9876\",\"autoCreateTopicEnable=true\"]\n" +
            "    depends_on:\n" +
            "      - mall4cloud-rocketmq-namesrv\n" +
            "\n" +
            "  mall4cloud-rocketmq-dashboard:\n" +
            "    image: apacherocketmq/rocketmq-dashboard:1.0.0\n" +
            "    container_name: mall4cloud-rocketmq-dashboard\n" +
            "    restart: always\n" +
            "    ports:\n" +
            "      - 8180:8080\n" +
            "    environment:\n" +
            "      JAVA_OPTS: \"-Drocketmq.namesrv.addr=mall4cloud-rocketmq-namesrv:9876 -Dcom.rocketmq.sendMessageWithVIPChannel=false\"\n" +
            "    depends_on:\n" +
            "      - mall4cloud-rocketmq-namesrv"+
            "\n"),
    ;

    private final String command;

    RocketmqShell(String command) {
        this.command = command;
    }

    @Override
    public String getCommand() {
        return command;
    }
}
