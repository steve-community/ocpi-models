package com.github.stevecommunity.ocpi.v221.web;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Accessors(chain = true)
@Data
public class OcpiResponsePaginationHeaders {
    String link;
    Integer totalCount;
    Integer limit;

    /**
     * To be used within the processing of an HTTP request, since {@link ServletUriComponentsBuilder#fromCurrentRequest()}
     * will try to find the HttpServletRequest in a ThreadLocal.
     */
    public static OcpiResponsePaginationHeaders create(int totalCount, OcpiRequestParameters currentParams) {
        int offset = currentParams.getOffset();
        int limit = currentParams.getLimit();
        int nextOffset = offset + limit;

        String link = null;
        if (nextOffset < totalCount) {
            var nextPageUrl = ServletUriComponentsBuilder.fromCurrentRequest()
                .replaceQueryParam("offset", nextOffset)
                .replaceQueryParam("limit", limit)
                .build()
                .toUriString();

            link = "<" + nextPageUrl + ">; rel=\"next\"";
        }

        return new OcpiResponsePaginationHeaders()
            .setLink(link)
            .setTotalCount(totalCount)
            .setLimit(limit);
    }
}
