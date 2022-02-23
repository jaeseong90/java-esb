package me.jaeseong.esb;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
public class CsProdInfoService {

    private static final String MAPPER_PRIFIX = "TEST"; //맵퍼 접두사
    private static final String MAPPER_NAME   = "CsProdInfoMapper";

    private static final Logger logger = LoggerFactory.getLogger(CsProdInfoService.class);


    @Autowired
    @Qualifier("SQL_SESSION")
    private SqlSessionTemplate sqlSession;

    @Transactional("DATASOURCE_TX_MANAGER")
    public int insert(@Body String body, @Headers Map<String, Object> headers) throws Exception {

        logger.info("Message parameter ===> {}", body);
        int result = 0;

        try {

            Gson gson = new GsonBuilder().serializeNulls().create();
            CsProdInfoDto parm = gson.fromJson(body, CsProdInfoDto.class);
            String gsonString = "{\"compCd\":\"02\",\"prodCd\":\"TEST01\",\"prodNm\":\"d-01\",\"makerNm\":\"aaa\",\"serialNo\":\"2022-009-00991\",\"regUserId\":\"jaeseong90\",\"regUserIP\":\"10.10.1.11\"}";

            result = sqlSession.insert(String.format("%s.%s.%s", MAPPER_PRIFIX, MAPPER_NAME,"insert"), parm);

        } catch (Exception e) {
            logger.error("",e);
            throw new Exception(e);
        }
        return result;
    }


}
