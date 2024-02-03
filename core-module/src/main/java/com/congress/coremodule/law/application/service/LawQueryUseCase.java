package com.congress.coremodule.law.application.service;

import com.congress.coremodule.law.application.dto.LawVoteReq;
import com.congress.coremodule.law.application.dto.LawVoteResult;
import com.congress.coremodule.law.application.dto.LegislatorReq;
import com.congress.coremodule.law.domain.service.LawQueryService;
import com.congress.coremodule.vote.application.dto.LawDetail;
import com.congress.coremodule.vote.application.dto.LawTotal;
import com.congress.coremodule.vote.application.dto.LegislatorDetail;
import com.congress.coremodule.vote.application.dto.LegislatorList;
import com.congress.coremodule.vote.domain.service.VoteQueryService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LawQueryUseCase {

    private final LawQueryService lawQueryService;
    private final VoteQueryService voteQueryService;

    public LawVoteResult getVoteResult(LawVoteReq req) {
        String apiUrl = "https://open.assembly.go.kr/portal/openapi/nwbpacrgavhjryiph?KEY=86f396b109764bb6bd688b181875d6ce&Type=json&pIndex=1&pSize=100&AGE=21"
                + "&BILL_NM=" + req.getLawName();
        LawVoteResult result = new LawVoteResult();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        String responseBody = responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataArray = rootNode.get("nwbpacrgavhjryiph").get(1).get("row");

            for (JsonNode dataNode : dataArray) {

                String billName = dataNode.get("BILL_NM").asText();
                String yesCount = dataNode.get("YES_TCNT").asText();

                result.setBillNm(billName);
                result.setYesCount(yesCount);

                return result;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<LawTotal> getTotalLaws() {
        String apiUrl = "https://open.assembly.go.kr/portal/openapi/nzmimeepazxkubdpn?KEY=86f396b109764bb6bd688b181875d6ce&Type=json&pIndex=1&pSize=100&AGE=21";
        List<LawTotal> lawTotals = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        String responseBody = responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataArray = rootNode.get("nzmimeepazxkubdpn").get(1).get("row");

            for (JsonNode dataNode : dataArray) {

                String billNo = dataNode.get("BILL_NO").asText();
                String billName = dataNode.get("BILL_NAME").asText();
                String proposer = dataNode.get("PROPOSER").asText();
                String proposeDt = dataNode.get("PROPOSE_DT").asText();
                String detailLink = dataNode.get("DETAIL_LINK").asText();

                Integer score = voteQueryService.getTotalScore(billName);

                if (score == null) {
                    score = 0;
                }

                LawTotal result = new LawTotal();
                result.setBillNo(billNo);
                result.setBillNm(billName);
                result.setProposer(proposer);
                result.setProposerDt(proposeDt);
                result.setDetailLink(detailLink);
                result.setScore(score);

                lawTotals.add(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(lawTotals, Comparator.comparing(LawTotal::getBillNm));
        return lawTotals;
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

                if (req.getLawName().equals(dataNode.get("BILL_NAME").asText())) {

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

                    if (!lawQueryService.isLawAlreadySaved(result.getBillNm())) {
                        lawQueryService.saveLaw(result.getBillNm());
                    }
                    return result;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public LegislatorDetail getLegislatorDetail(LegislatorReq req) {
        String apiUrl = "https://open.assembly.go.kr/portal/openapi/npffdutiapkzbfyvr?KEY=86f396b109764bb6bd688b181875d6ce&Type=json&pIndex=1&pSize=100&UNIT_CD=100020";
        LegislatorDetail result = new LegislatorDetail();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        String responseBody = responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataArray = rootNode.get("npffdutiapkzbfyvr").get(1).get("row");

            for (JsonNode dataNode : dataArray) {

                if (req.getLegislatorName().equals(dataNode.get("HG_NM").asText())) {

                    String hgNm = dataNode.get("HG_NM").asText();
                    String bthDate = dataNode.get("BTH_DATE").asText();
                    String sexGbnNm = dataNode.get("SEX_GBN_NM").asText();
                    String reeleGbnNm = dataNode.get("REELE_GBN_NM").asText();
                    String units = dataNode.get("UNITS").asText();
                    String unitNm = dataNode.get("UNIT_NM").asText();
                    String polyNm = dataNode.get("POLY_NM").asText();
                    String origNm = dataNode.get("ORIG_NM").asText();

                    result.setHgNm(hgNm);
                    result.setBthDate(bthDate);
                    result.setSexGbnNm(sexGbnNm);
                    result.setReeleGbnNm(reeleGbnNm);
                    result.setUnits(units);
                    result.setUnitNm(unitNm);
                    result.setPolyNm(polyNm);
                    result.setOrigNm(origNm);

                    if (!lawQueryService.isLegislatorAlreadySaved(result.getHgNm())) {
                        lawQueryService.saveLegislator(result.getHgNm());
                    }
                    return result;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<LegislatorList> getTotalLegislators() {
        String apiUrl = "https://open.assembly.go.kr/portal/openapi/npffdutiapkzbfyvr?KEY=86f396b109764bb6bd688b181875d6ce&Type=json&pIndex=1&pSize=100&UNIT_CD=100020";
        List<LegislatorList> lawTotals = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        String responseBody = responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataArray = rootNode.get("npffdutiapkzbfyvr").get(1).get("row");

            for (JsonNode dataNode : dataArray) {

                String hgNm = dataNode.get("HG_NM").asText();
                String polyNm = dataNode.get("POLY_NM").asText();
                String unitNm = dataNode.get("UNIT_NM").asText();

                Integer score = voteQueryService.getLegislatorTotalScore(hgNm);

                if (score == null) {
                    score = 0;
                }

                LegislatorList result = new LegislatorList();
                result.setName(hgNm);
                result.setSection(polyNm);
                result.setUnit(unitNm);
                result.setScore(score);

                lawTotals.add(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        lawTotals.sort(Comparator.comparing(LegislatorList::getName));
        return lawTotals;
    }

}
