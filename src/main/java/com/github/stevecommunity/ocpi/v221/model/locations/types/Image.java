package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Accessors(chain = true)
@Data
public class Image {
    /**
     * URL from where the image data can be fetched through a web browser.
     */
    @NotEmpty @Size(max = 255) String url;

    @Size(max = 255) String thumbnail;

    /**
     * Describes what the image is used for.
     */
    @NotNull ImageCategory category;

    /**
     * Image type like: gif, jpeg, png, svg.
     */
    @NotEmpty @Size(max = 4) String type;

    /**
     * Width of the image in pixels.
     */
    Integer width;

    /**
     * Height of the image in pixels.
     */
    Integer height;
}
