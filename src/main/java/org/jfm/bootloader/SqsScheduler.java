package org.jfm.bootloader;

import org.jfm.domain.services.VideoShieldSyncService;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SqsScheduler {

  @Inject
  VideoShieldSyncService service;

  @Scheduled(every = "30s") 
  void pollSqsQueue() {
    service.receberMensagens();
  }
}
