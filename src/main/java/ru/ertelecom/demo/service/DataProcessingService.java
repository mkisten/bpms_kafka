package ru.ertelecom.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ertelecom.demo.model.*;
import ru.ertelecom.demo.repository.*;

import java.util.List;

@Service
public class DataProcessingService {

    @Autowired
    private BpBusinessDataFaultHistoryRepository bpBusinessDataFaultHistoryRepository;
    @Autowired
    private BpBusinessDataFaultTaskAssignmentRepository bpBusinessDataFaultTaskAssignmentRepository;
    @Autowired
    private BpBusinessDataFaultTasksRepository bpBusinessDataFaultTasksRepository;
    @Autowired
    private BpBusinessDataFaultToroRepository bpBusinessDataFaultToroRepository;
    @Autowired
    private BpBusinessDataFaultSapRepository bpBusinessDataFaultSapRepository;
    @Autowired
    private BpFaultBrigadesMatrixRepository bpFaultBrigadesMatrixRepository;
    @Autowired
    private BpBusinessDataFaultCommentNewRepository bpBusinessDataFaultCommentNewRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Transactional
    public void processAndSendData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<BpBusinessDataFaultHistory> faultHistories = bpBusinessDataFaultHistoryRepository.findAll();
        for (BpBusinessDataFaultHistory faultHistory : faultHistories) {
            String json = objectMapper.writeValueAsString(faultHistory);
            kafkaProducer.sendMessage(json);
        }

        List<BpBusinessDataFaultTaskAssignment> taskAssignments = bpBusinessDataFaultTaskAssignmentRepository.findAll();
        for (BpBusinessDataFaultTaskAssignment taskAssignment : taskAssignments) {
            String json = objectMapper.writeValueAsString(taskAssignment);
            kafkaProducer.sendMessage(json);
        }

        List<BpBusinessDataFaultTasks> tasks = bpBusinessDataFaultTasksRepository.findAll();
        for (BpBusinessDataFaultTasks task : tasks) {
            String json = objectMapper.writeValueAsString(task);
            kafkaProducer.sendMessage(json);
        }

        List<BpBusinessDataFaultToro> toros = bpBusinessDataFaultToroRepository.findAll();
        for (BpBusinessDataFaultToro toro : toros) {
            String json = objectMapper.writeValueAsString(toro);
            kafkaProducer.sendMessage(json);
        }

        List<BpBusinessDataFaultSap> saps = bpBusinessDataFaultSapRepository.findAll();
        for (BpBusinessDataFaultSap sap : saps) {
            String json = objectMapper.writeValueAsString(sap);
            kafkaProducer.sendMessage(json);
        }

        List<BpFaultBrigadesMatrix> brigades = bpFaultBrigadesMatrixRepository.findAll();
        for (BpFaultBrigadesMatrix brigade : brigades) {
            String json = objectMapper.writeValueAsString(brigade);
            kafkaProducer.sendMessage(json);
        }

        List<BpBusinessDataFaultCommentNew> comments = bpBusinessDataFaultCommentNewRepository.findAll();
        for (BpBusinessDataFaultCommentNew comment : comments) {
            String json = objectMapper.writeValueAsString(comment);
            kafkaProducer.sendMessage(json);
        }
    }
}
