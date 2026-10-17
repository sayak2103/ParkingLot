package com.mini_project.parkingLot.dto;

import java.time.Instant;

public record ApiError(Instant timeStamp, int status, String err, String msg, String path) {

}
