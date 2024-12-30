FROM gitpod/workspace-full:latest

# Install Java 11 (or any version you need)
RUN sudo apt update && sudo apt install -y openjdk-11-jdk && \
    sudo apt-get clean

# Set Java 11 as default
RUN update-java-alternatives --set java-1.11.0-openjdk-amd64

# Install Maven
RUN sudo apt install -y maven
