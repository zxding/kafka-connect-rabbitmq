/**
 * Copyright © 2017 Jeremy Custenborder (jcustenborder@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jcustenborder.kafka.connect.rabbitmq;

import com.github.jcustenborder.kafka.connect.utils.template.StructTemplate;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

class RabbitMQSourceConnectorConfig extends RabbitMQConnectorConfig {

  static final String KAFKA_TOPIC_TEMPLATE = "kafkaTopicTemplate";
  public static final String TOPIC_CONF = "kafka.topic";
  static final String TOPIC_DOC = "Kafka topic to write the messages to.";

  public final StructTemplate kafkaTopic;

  public RabbitMQSourceConnectorConfig(Map<String, String> settings) {
    super(config(), settings);

    final String kafkaTopicFormat = this.getString(TOPIC_CONF);
    this.kafkaTopic = new StructTemplate();
    this.kafkaTopic.addTemplate(KAFKA_TOPIC_TEMPLATE, kafkaTopicFormat);
  }

  public static ConfigDef config() {
    return RabbitMQConnectorConfig.config()
        .define(TOPIC_CONF, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, TOPIC_DOC);
  }
}