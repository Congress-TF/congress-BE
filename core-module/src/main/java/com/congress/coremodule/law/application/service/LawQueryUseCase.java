package com.congress.coremodule.law.application.service;

import com.congress.coremodule.law.application.dto.LawVoteReq;
import com.congress.coremodule.law.application.dto.LawVoteResult;
import com.congress.coremodule.law.domain.service.LawQueryService;
import com.congress.coremodule.vote.application.dto.LawDetail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LawQueryUseCase {

    private final LawQueryService lawQueryService;

    public LawVoteResult getVoteResult(LawVoteReq req) {
        String apiUrl = "https://open.assembly.go.kr/portal/openapi/nwbpacrgavhjryiph?KEY=86f396b109764bb6bd688b181875d6ce&Type=json&pIndex=1&pSize=100&AGE=21";
        LawVoteResult result = new LawVoteResult();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        String responseBody = responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataArray = rootNode.get("nwbpacrgavhjryiph").get(1).get("row");

            for (JsonNode dataNode : dataArray) {

                if (req.getLawId().toString().equals(dataNode.get("BILL_NO").asText())) {

                    String billName = dataNode.get("BILL_NM").asText();
                    String yesCount = dataNode.get("YES_TCNT").asText();

                    result.setBillNm(billName);
                    result.setYesCount(yesCount);

                    return result;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public LawDetail getLawDetail(LawVoteReq req) {
        String apiUrl = "https://open.assembly.go.kr/portal/openapi/nzmimeepazxkubdpn?KEY=86f396b109764bb6bd688b181875d6ce&Type=json&pIndex=1&pSize=100&AGE=21";
        LawDetail result = new LawDetail();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        String responseBody = responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataArray = rootNode.get("nzmimeepazxkubdpn").get(1).get("row");

            for (JsonNode dataNode : dataArray) {

                if (req.getLawId().toString().equals(dataNode.get("BILL_NO").asText())) {

                    String billNo = dataNode.get("BILL_NO").asText();
                    String billName = dataNode.get("BILL_NAME").asText();
                    String proposer = dataNode.get("PROPOSER").asText();
                    String proposeDt = dataNode.get("PROPOSE_DT").asText();
                    String detailLink = dataNode.get("DETAIL_LINK").asText();

                    result.setBillNo(billNo);
                    result.setBillNm(billName);
                    result.setProposer(proposer);
                    result.setProposerDt(proposeDt);
                    result.setDetailLink(detailLink);

                    return result;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}
