package com.congress.coremodule.member.domain.exception;

import com.congress.commonmodule.exception.BusinessException;
import com.congress.commonmodule.exception.Error;

public class MemberException extends BusinessException {

    public MemberException(Error error) {
        super(error);
    }
}
