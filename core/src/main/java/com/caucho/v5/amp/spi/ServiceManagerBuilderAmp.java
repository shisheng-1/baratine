/*
 * Copyright (c) 1998-2015 Caucho Technology -- all rights reserved
 *
 * This file is part of Baratine(TM)
 *
 * Each copy or derived work must preserve the copyright notice and this
 * notice unmodified.
 *
 * Baratine is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * Baratine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, or any warranty
 * of NON-INFRINGEMENT.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Baratine; if not, write to the
 *
 *   Free Software Foundation, Inc.
 *   59 Temple Place, Suite 330
 *   Boston, MA 02111-1307  USA
 *
 * @author Scott Ferguson
 */

package com.caucho.v5.amp.spi;

import java.util.concurrent.Executor;
import java.util.function.Supplier;

import com.caucho.v5.amp.ServicesAmp;
import com.caucho.v5.amp.journal.JournalFactoryAmp;
import com.caucho.v5.amp.manager.ServiceManagerBuilderImpl;
import com.caucho.v5.amp.proxy.ProxyFactoryAmp;
import com.caucho.v5.amp.stub.StubGenerator;
import com.caucho.v5.inject.InjectorAmp;

import io.baratine.service.QueueFullHandler;
import io.baratine.service.Services;
import io.baratine.service.ServiceNode;

/**
 * Creates a AMP domain.
 */
public interface ServiceManagerBuilderAmp extends Services.ServicesBuilder
{
  public static ServiceManagerBuilderAmp newManager()
  {
    return new ServiceManagerBuilderImpl();
  }
  
  /**
   * Gets the name of the manager.
   */
  public String name();

  /**
   * Sets the name of the manager
   */
  ServiceManagerBuilderAmp name(String name);

  ServiceManagerBuilderAmp classLoader(ClassLoader classLoader);
  ClassLoader classLoader();
  
  void debugId(String name);
  
  String debugId();

  /**
   * Returns the actor proxy factory.
   */
  ProxyFactoryAmp proxyFactory();

  /**
   * Sets the proxy context factory.
   */
  ServiceManagerBuilderAmp proxyFactory(ProxyFactoryAmp factory);

  /**
   * Returns the actor journal factory.
   */
  JournalFactoryAmp journalFactory();

  /**
   * Sets the journal context factory.
   */
  ServiceManagerBuilderAmp journalFactory(JournalFactoryAmp factory);
  
  ServiceManagerBuilderAmp journalMaxCount(int journalMaxCount);
  
  ServiceManagerBuilderAmp setJournalDelay(long journalTimeout);
  long getJournalDelay();

  QueueFullHandler getQueueFullHandler();
  ServiceManagerBuilderAmp queueFullHandler(QueueFullHandler handler);
  
  Supplier<Executor> systemExecutor();
  
  ServiceManagerBuilderAmp contextManager(boolean isContext);
  boolean isContextManager();

  ServiceManagerBuilderAmp autoStart(boolean isAutoStart);
  boolean isAutoStart();
  
  ServiceManagerBuilderAmp autoServices(boolean isAutoServices);
  boolean isAutoServices();


  ServiceManagerBuilderAmp stubGenerator(StubGenerator factory);
  StubGenerator[] stubGenerators();
  
  Supplier<InjectorAmp> injectManager(ServicesAmp ampManager);
  ServiceManagerBuilderAmp injectManager(Supplier<InjectorAmp> inject);
  
  /**
   * Returns the pod node.
   */
  ServiceNode podNode();
  ServiceManagerBuilderAmp podNode(ServiceNode podNode);

  
  //
  // debugging
  //
  
  boolean isDebug();
  ServiceManagerBuilderAmp debug(boolean isDebug);

  long getDebugQueryTimeout();
  ServiceManagerBuilderAmp debugQueryTimeout(long timeout);
  
  /**
   * Creates the manager and start the services.
   */
  @Override
  ServicesAmp start();
  
  /**
   * Creates the manager without starting the services.
   */
  @Override
  ServicesAmp get();

  ServicesAmp getRaw();


  /**
   * returns the build manager
   */
  //ServiceManagerAmp managerBuild();
}
