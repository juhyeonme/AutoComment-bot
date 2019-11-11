package com.juhyeon.aucobot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.social.github.connect.GitHubConnectionFactory;


@Configuration
@ConditionalOnClass({GitHubConnectionFactory.class, SocialConfigurerAdapter.class})
@ConditionalOnProperty(prefix = "spring.social.github", name = "client-Id")
@EnableConfigurationProperties(GitHubProperties.class)
@ConditionalOnWebApplication
@EnableSocial
public class GitHubApiConfiguration extends SocialConfigurerAdapter {

    private GitHubProperties githubProperties;

    @Autowired
    public void setGitHubProperties(GitHubProperties githubProperties) {
        this.githubProperties = githubProperties;
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    private ConnectionFactory createConnectionFactory() {
        return new GitHubConnectionFactory(this.githubProperties.getClientId(),
                                        this.githubProperties.getClientSecret());
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public GitHub github(ConnectionRepository connectionRepository) {
        Connection<GitHub> connection = connectionRepository
                                        .getPrimaryConnection(GitHub.class);
        if(connection != null) {
            return connection.getApi();
        }

        return new GitHubTemplate(this.githubProperties.getClientSecret());
    }


}
