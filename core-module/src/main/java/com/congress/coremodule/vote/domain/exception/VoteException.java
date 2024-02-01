package com.congress.coremodule.vote.domain.exception;

import com.congress.commonmodule.exception.BusinessException;
import com.congress.commonmodule.exception.Error;

public class VoteException extends BusinessException {

    public VoteException(Error error) {
        super(error);
    }
}
