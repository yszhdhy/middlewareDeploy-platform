package com.example.middlewaredeploy.constant;

public enum DockerShell implements Adapter{
    DELETE_DOCKER("sudo yum -y remove docker*"),
    YUM_UTILS("sudo yum install -y yum-utils"),
    ADD_REPO("sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo"),
    DOCKER_CE("sudo yum install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin"),
    ENABLE_DOCKER("sudo systemctl enable docker --now"),
    DISPOSITION_DOCKER("sudo mkdir -p /etc/docker"),
    DOCKER_MIRRORS("sudo tee /etc/docker/daemon.json <<-'EOF'\n" +
            "{\n" +
            "  \"registry-mirrors\": [\"https://82m9ar63.mirror.aliyuncs.com\"],\n" +
            "  \"exec-opts\": [\"native.cgroupdriver=systemd\"],\n" +
            "  \"log-driver\": \"json-file\",\n" +
            "  \"log-opts\": {\n" +
            "    \"max-size\": \"100m\"\n" +
            "  },\n" +
            "  \"storage-driver\": \"overlay2\"\n" +
            "}\n" +
            "EOF"),
    DAEMON_RELOAD("sudo systemctl daemon-reload"),
    RESTART_DOCKER("sudo systemctl restart docker"),

    DOCKER_PS("docker ps"),
    DOCKER_COMPOSE("docker compose")
    ;

    private final String command;

    DockerShell(String command) {
        this.command = command;
    }

    @Override
    public String getCommand() {

        return command;
    }
}
