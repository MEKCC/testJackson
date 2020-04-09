package com.practice.csvCreatorImpl;

import com.practice.csvCreeator.CSVPresidentDatasetService;
import com.practice.entity.President;
import com.practice.repo.PresidentRepo;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metamodel.spi.MetamodelImplementor;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Service
public class CSVPresidentDatasetServiceImpl implements CSVPresidentDatasetService {

    @Autowired
    private PresidentRepo presidentRepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createCSVDataset() {
//        List<President> presidentListt = presidentRepo.findAll();
//
//        President presidentt = presidentListt.get(0);
//
//        SessionFactory sessionFactory = entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
//        ClassMetadata hibernateMetadata = sessionFactory.getClassMetadata(President.class);
//        AbstractEntityPersister persister = (AbstractEntityPersister) hibernateMetadata;
//        for (Field field : presidentt.getClass().getFields()) {
//            String columnName;
//            if (field.isAnnotationPresent(Column.class)) {
//                columnName = field.getAnnotation(Column.class).name();
//                System.out.println(columnName);
//            } else {
//                String[] columnNames = persister.getPropertyColumnNames(field.getName());
//                if (columnNames.length > 0) {
//                    columnName = columnNames[0];
//                    System.out.println(columnName);
//                }
//            }
//        }


        List<President> presidentList = presidentRepo.findAll();

        File file = new File("test" + ".csv");
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(String.join(",",
                    getColumnNameFromDB("id"),
                    getColumnNameFromDB("name"),
                    getColumnNameFromDB("age")));
            writer.append("\n");
            for (President president : presidentList) {
                writer.append(String.join(",", String.valueOf(president.getId()), president.getName(), president.getAge()));
                writer.append("\n");
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    private String getColumnNameFromDB(String fieldName) {
        String[] oneFieldInArray = ((AbstractEntityPersister) ((MetamodelImplementor) entityManager.getEntityManagerFactory().unwrap(
                SessionFactory.class).getMetamodel()).entityPersister(President.class))
                .getPropertyColumnNames(fieldName);
        return oneFieldInArray[0];
    }
}
