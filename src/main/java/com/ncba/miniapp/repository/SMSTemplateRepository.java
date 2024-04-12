package com.ncba.miniapp.repository;

import com.ncba.miniapp.model.SMSTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SMSTemplateRepository extends JpaRepository<SMSTemplate, Long> {
    public SMSTemplate findByTemplateId(String templateId);
}
