package com.nace.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.nace.constants.NaceEnum;
import com.nace.dto.AddedNaceDetailsDto;
import com.nace.dto.GetNaceDetailsDto;
import com.nace.entities.NaceDetailsEntity;
import com.nace.exceptions.EntityNotFoundException;

/**
 * Maps Entity object to DTO object for API responses.
 *
 *
 */
@Component
public class ApiResponseBuilder {

    /**
     * Maps the list of entity to dto object for api operation of POST http method.
     * 
     * @param result - List of type NaceDetailsEntity - Persisted list of entity objects.
     * @return ResponseEntity of type AddNaceDetails
     */
    public ResponseEntity<AddedNaceDetailsDto> buildPostResponse(List<NaceDetailsEntity> result) {

        AddedNaceDetailsDto returnDto = null;

        if (!Objects.isNull(result) && result.size() > 0) {
            returnDto = AddedNaceDetailsDto.builder().status(NaceEnum.STATUS_SUCCESS.getValue())
                    .details(Arrays.asList(NaceEnum.ADD_RECORDS_SUCCESS_MSG.getValue()
                            .concat(StringUtils.defaultString(String.valueOf(result.size()), NaceEnum.ZERO.getValue())
                                    .concat(NaceEnum.RECORDS_MSG_TXT.getValue()))))
                    .timestamp(LocalDateTime.now()).build();
        } else {
            returnDto = AddedNaceDetailsDto.builder().status(NaceEnum.STATUS_FAILED.getValue())
                    .details(Arrays.asList(NaceEnum.ADD_RECORDS_FAILURE_MSG.getValue()
                            .concat(StringUtils.defaultString(String.valueOf(result.size()), NaceEnum.ZERO.getValue())
                                    .concat(NaceEnum.RECORDS_MSG_TXT.getValue()))))
                    .timestamp(LocalDateTime.now()).build();
        }
        return new ResponseEntity<AddedNaceDetailsDto>(returnDto, HttpStatus.OK);
    }

    /**
     * Maps the list of entity to dto object for api operation of GET http method.
     * 
     * @param resultArg - List of type NaceDetailsEntity - Persisted list of entity objects.
     * @return ResponseEntity of type list of type GetNaceDetails
     * @throws EntityNotFoundException
     */
    public ResponseEntity<List<GetNaceDetailsDto>> buildGetResponse(List<NaceDetailsEntity> resultArg)
            throws EntityNotFoundException {

        List<GetNaceDetailsDto> returnDtoList = new ArrayList<GetNaceDetailsDto>();

        if (!Objects.isNull(resultArg) && resultArg.size() > 0) {

            resultArg.stream().forEach(result -> {
                returnDtoList.add(GetNaceDetailsDto.builder()
                        .order(result.getOrder())
                        .level(result.getLevel())
                        .code(result.getCode())
                        .parent(result.getParent())
                        .description(result.getDescription())
                        .itemIncludes(result.getItemIncludes())
                        .itemAlsoIncludes(result.getItemAlsoIncludes())
                        .rulings(result.getRulings())
                        .itemExcludes(result.getItemExcludes())
                        .referencesIsic(result.getReferencesIsic())
                        .build());
            });
        } else {
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<List<GetNaceDetailsDto>>(returnDtoList, HttpStatus.OK);
    }
}
