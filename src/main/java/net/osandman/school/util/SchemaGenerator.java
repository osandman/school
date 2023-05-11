package net.osandman.school.util;

import org.hibernate.boot.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.schema.TargetType;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.util.EnumSet;

public class SchemaGenerator {
    public static <T> void exportCreateQuery(Class<T> clazz, String filename) {
        try (ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build()) {
            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            metadataSources.addAnnotatedClass(clazz);
            Metadata metadata = metadataSources.buildMetadata();

            SchemaExport schemaExport = new SchemaExport();
            schemaExport.setFormat(true);
            schemaExport.setOutputFile(filename);
            schemaExport.createOnly(EnumSet.of(TargetType.SCRIPT), metadata);
        }
    }
}
