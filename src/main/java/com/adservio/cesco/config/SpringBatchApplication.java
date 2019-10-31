package com.adservio.cesco.config;

import com.adservio.cesco.config.mission.OriginMissionItemWriter;
import com.adservio.cesco.config.mission.OriginMissionProcessor;
import com.adservio.cesco.config.station.StationItemProcessor;
import com.adservio.cesco.config.station.StationItemWriter;
import com.adservio.cesco.config.typeMission.TypeMissionItemWriter;
import com.adservio.cesco.config.typeMission.TypeMissionProcessor;
import com.adservio.cesco.domain.OriginMission;
import com.adservio.cesco.domain.Station;
import com.adservio.cesco.domain.TypeMission;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SpringBatchApplication {

    private OriginMissionItemWriter originMissionItemWriter;

    private OriginMissionProcessor originMissionProcessor;

    private StationItemProcessor stationItemProcessor;

    private StationItemWriter stationItemWriter;

    private TypeMissionProcessor typeMissionProcessor;

    private TypeMissionItemWriter typeMissionItemWriter;

    @Value("classpath:files/origines.csv")
    Resource originesResource;

    @Value("classpath:files/typesMissions.csv")
    Resource typesMissionsResource;

    @Value("classpath:files/stations.csv")
    Resource stationsResource;

    public SpringBatchApplication(OriginMissionItemWriter originMissionItemWriter, OriginMissionProcessor originMissionProcessor, StationItemProcessor stationItemProcessor, StationItemWriter stationItemWriter, TypeMissionProcessor typeMissionProcessor,
                                  TypeMissionItemWriter typeMissionItemWriter) {
        this.originMissionItemWriter = originMissionItemWriter;
        this.originMissionProcessor = originMissionProcessor;
        this.stationItemProcessor = stationItemProcessor;
        this.stationItemWriter = stationItemWriter;
        this.typeMissionProcessor = typeMissionProcessor;
        this.typeMissionItemWriter = typeMissionItemWriter;
    }


    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        return jobBuilderFactory.get("cesco")
                .start(importMission(stepBuilderFactory))
                .next(importTypeMission(stepBuilderFactory))
                .next(importStation(stepBuilderFactory))
                .build();
    }

    @Bean
    public Step importStation(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("ETL-file-load")
                .<Station, Station>chunk(2000)
                .reader(getStationItemReader())
                .processor(stationItemProcessor)
                .writer(stationItemWriter)
                .build();
    }

    private ItemReader<? extends Station> getStationItemReader() {
        FlatFileItemReader<Station> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(stationsResource);
        flatFileItemReader.setName("CSV-Station-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(stationLineMapper());
        return flatFileItemReader;
    }

    private LineMapper<Station> stationLineMapper() {
        DefaultLineMapper<Station> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"id", "libelle", "latitude", "longitude"});
        BeanWrapperFieldSetMapper<Station> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Station.class);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }

    @Bean
    public Step importTypeMission(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("ETL-file-load")
                .<TypeMission, TypeMission>chunk(2000)
                .reader(getTypeMissionReader())
                .processor(typeMissionProcessor)
                .writer(typeMissionItemWriter)
                .build();
    }

    private ItemReader<? extends TypeMission> getTypeMissionReader() {
        FlatFileItemReader<TypeMission> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(typesMissionsResource);
        flatFileItemReader.setName("CSV-TypeMission-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(typeMissionLineMapper());
        return flatFileItemReader;
    }

    private LineMapper<TypeMission> typeMissionLineMapper() {
        DefaultLineMapper<TypeMission> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"id", "libelle"});
        BeanWrapperFieldSetMapper<TypeMission> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(TypeMission.class);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }

    @Bean
    public Step importMission(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("ETL-file-load")
                .<OriginMission, OriginMission>chunk(2000)
                .reader(getOriginMissionitemReader())
                .processor(originMissionProcessor)
                .writer(originMissionItemWriter)
                .build();
    }


    @Bean
    public ItemReader<? extends OriginMission> getOriginMissionitemReader() {
        FlatFileItemReader<OriginMission> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(originesResource);
        flatFileItemReader.setName("CSV-Origines-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(originMissionLineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<OriginMission> originMissionLineMapper() {
        DefaultLineMapper<OriginMission> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"id", "libelle"});
        BeanWrapperFieldSetMapper<OriginMission> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(OriginMission.class);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }


}
