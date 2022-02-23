package me.jaeseong.esb;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;


@Getter
@Setter
@ToString
@Component
public class EsbRcvResultVo {

    public static final String ESB_RCV_RESULT_OK    = "OK";
    public static final String ESB_RCV_RESULT_ERROR = "ER";

    private String EsbRcvResultCd;
    private String EsbRcvResultMessage;

}
