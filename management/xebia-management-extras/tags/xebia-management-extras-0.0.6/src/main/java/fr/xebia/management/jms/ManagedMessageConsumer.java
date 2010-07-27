/*
 * Copyright 2008-2009 Xebia and the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.xebia.management.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;

import fr.xebia.jms.wrapper.MessageConsumerWrapper;

/**
 * 
 * @author <a href="mailto:cyrille@cyrilleleclerc.com">Cyrille Le Clerc</a>
 */
public class ManagedMessageConsumer extends MessageConsumerWrapper {

    private final JmsStatistics statistics;

    public ManagedMessageConsumer(MessageConsumer delegate, JmsStatistics statistics) {
        super(delegate);
        this.statistics = statistics;
    }

    @Override
    public Message receive() throws JMSException {
        long timeBefore = System.currentTimeMillis();
        Message message = null;
        try {
            message = super.receive();
        } catch (JMSException e) {
            statistics.incrementReceivedMessageExceptionCount();
            throw e;
        } catch (RuntimeException e) {
            statistics.incrementReceivedMessageExceptionCount();
            throw e;
        } finally {
            if (message != null) {
                statistics.incrementReceivedMessageCount();
            }
            statistics.incrementReceiveMessageDurationInMillis(System.currentTimeMillis() - timeBefore);
        }
        return message;
    }

    @Override
    public Message receive(long timeout) throws JMSException {
        long timeBefore = System.currentTimeMillis();
        Message message = null;
        try {
            message = super.receive(timeout);
        } catch (JMSException e) {
            statistics.incrementReceivedMessageExceptionCount();
            throw e;
        } catch (RuntimeException e) {
            statistics.incrementReceivedMessageExceptionCount();
            throw e;
        } finally {
            if (message != null) {
                statistics.incrementReceivedMessageCount();
            }
            statistics.incrementReceiveMessageDurationInMillis(System.currentTimeMillis() - timeBefore);
        }
        return message;
    }

    @Override
    public Message receiveNoWait() throws JMSException {
        long timeBefore = System.currentTimeMillis();
        Message message = null;
        try {
            message = super.receiveNoWait();
        } catch (JMSException e) {
            statistics.incrementReceivedMessageExceptionCount();
            throw e;
        } catch (RuntimeException e) {
            statistics.incrementReceivedMessageExceptionCount();
            throw e;
        } finally {
            if (message != null) {
                statistics.incrementReceivedMessageCount();
            }
            statistics.incrementReceiveMessageDurationInMillis(System.currentTimeMillis() - timeBefore);
        }
        return message;
    }
}
