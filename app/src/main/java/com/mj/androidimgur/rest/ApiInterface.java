package com.mj.androidimgur.rest;

import com.mj.androidimgur.model.GalleryResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mengjie.huang on 30/8/2018.
 *
 * https://apidocs.imgur.com/
 */

public interface ApiInterface {
    /**
     *
     * https://api.imgur.com/3/gallery/{{section}}/{{sort}}/{{window}}/{{page}}
     * ?showViral=true&mature=true&album_previews=true
     */

    /**
     * @param section        Optional. hot | top | user. Defaults to hot
     * @param sort           Optional. viral | top | time | rising (only available with user section).
     *                       Defaults to viral
     * @param window         Optional. Change the date range of the request if the section is top.
     *                       Accepted values are day | week | month | year | all. Defaults to day
     * @param page           Optional. integer - the data paging number
     * @param showViral      Optional. `true` | `false` - Show or hide viral images from the `user`
     *                       section. Defaults to `true`
     * @param mature         Optional. `true` | `false` - Show or hide mature (nsfw) images in the
     *                       response section. Defaults to `false` *NOTE:* This parameter is only
     *                       required if un-authed. The response for authed users will respect
     *                       their account setting.
     * @param album_previews Optional. `true` | `false` - Include image metadata for gallery posts
     *                       which are albums
     * @return
     */
    @Headers("Authorization: Client-ID 3e9a42907525a21")
    @GET("gallery/{section}/{sort}/{window}/{page}")
    Single<GalleryResponse> getGalleryDetails(@Path("section") String section,
                                                     @Path("sort") String sort,
                                                     @Path("window") String window,
                                                     @Path("page") int page,
                                                     @Query("showViral") boolean showViral,
                                                     @Query("mature") boolean mature,
                                                     @Query("album_previews") boolean album_previews);
}
