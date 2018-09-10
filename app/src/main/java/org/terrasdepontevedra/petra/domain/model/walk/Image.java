package org.terrasdepontevedra.petra.domain.model.walk;

public class Image {

    private int id;
    private String urlMedium;

    public Image(int id, String urlMedium) {
        this.id = id;
        this.urlMedium = urlMedium;
    }

    public Image(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlMedium() {
        if(urlMedium==null){
            return "";
        }
        return urlMedium;
    }

    public void setUrlMedium(String urlMedium) {
        this.urlMedium = urlMedium;
    }


    /**
     * id : 19
     * date : 2017-05-03T09:08:04
     * date_gmt : 2017-05-03T07:08:04
     * guid : {"rendered":"http://vigoexperience.com/wp-content/uploads/2014/09/vigoe.jpg"}
     * modified : 2017-05-03T09:08:04
     * modified_gmt : 2017-05-03T07:08:04
     * slug : vigoe
     * status : inherit
     * type : attachment
     * link : https://vigoexperience.com/coming-soon-page/vigoe/
     * title : {"rendered":"vigoe"}
     * author : 1
     * comment_status : open
     * ping_status : closed
     * template :
     * meta : []
     * rest_api_enabler : []
     * description : {"rendered":"<p class=\"attachment\"><a href='https://vigoexperience.com/wp-content/uploads/2014/09/vigoe.jpg'><img width=\"300\" height=\"225\" src=\"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-300x225.jpg\" class=\"attachment-medium size-medium\" alt=\"\" srcset=\"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-300x225.jpg 300w, https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-768x576.jpg 768w, https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-1030x773.jpg 1030w, https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-1500x1125.jpg 1500w, https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-705x529.jpg 705w, https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-450x338.jpg 450w, https://vigoexperience.com/wp-content/uploads/2014/09/vigoe.jpg 1600w\" sizes=\"(max-width: 300px) 100vw, 300px\" /><\/a><\/p>\n"}
     * caption : {"rendered":""}
     * alt_text :
     * media_type : image
     * mime_type : image/jpeg
     * media_details : {"width":1600,"height":1200,"file":"2014/09/vigoe.jpg","sizes":{"thumbnail":{"file":"vigoe-80x80.jpg","width":80,"height":80,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-80x80.jpg"},"medium":{"file":"vigoe-300x225.jpg","width":300,"height":225,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-300x225.jpg"},"medium_large":{"file":"vigoe-768x576.jpg","width":768,"height":576,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-768x576.jpg"},"large":{"file":"vigoe-1030x773.jpg","width":1030,"height":773,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-1030x773.jpg"},"widget":{"file":"vigoe-36x36.jpg","width":36,"height":36,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-36x36.jpg"},"square":{"file":"vigoe-180x180.jpg","width":180,"height":180,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-180x180.jpg"},"featured":{"file":"vigoe-1500x430.jpg","width":1500,"height":430,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-1500x430.jpg"},"featured_large":{"file":"vigoe-1500x630.jpg","width":1500,"height":630,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-1500x630.jpg"},"extra_large":{"file":"vigoe-1500x1125.jpg","width":1500,"height":1125,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-1500x1125.jpg"},"portfolio":{"file":"vigoe-495x400.jpg","width":495,"height":400,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-495x400.jpg"},"portfolio_small":{"file":"vigoe-260x185.jpg","width":260,"height":185,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-260x185.jpg"},"gallery":{"file":"vigoe-845x684.jpg","width":845,"height":684,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-845x684.jpg"},"magazine":{"file":"vigoe-710x375.jpg","width":710,"height":375,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-710x375.jpg"},"masonry":{"file":"vigoe-705x529.jpg","width":705,"height":529,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-705x529.jpg"},"entry_with_sidebar":{"file":"vigoe-845x321.jpg","width":845,"height":321,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-845x321.jpg"},"entry_without_sidebar":{"file":"vigoe-1210x423.jpg","width":1210,"height":423,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-1210x423.jpg"},"shop_thumbnail":{"file":"vigoe-120x120.jpg","width":120,"height":120,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-120x120.jpg"},"shop_catalog":{"file":"vigoe-450x450.jpg","width":450,"height":450,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-450x450.jpg"},"shop_single":{"file":"vigoe-450x338.jpg","width":450,"height":338,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe-450x338.jpg"},"full":{"file":"vigoe.jpg","width":1600,"height":1200,"mime_type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2014/09/vigoe.jpg"}},"image_meta":{"aperture":"0","credit":"","camera":"","caption":"","created_timestamp":"0","copyright":"","focal_length":"0","iso":"0","shutter_speed":"0","title":"","orientation":"0","keywords":[]}}
     * post : 14
     * source_url : https://vigoexperience.com/wp-content/uploads/2014/09/vigoe.jpg
     * _links : {"self":[{"href":"https://vigoexperience.com/wp-json/wp/v2/media/19"}],"collection":[{"href":"https://vigoexperience.com/wp-json/wp/v2/media"}],"about":[{"href":"https://vigoexperience.com/wp-json/wp/v2/types/attachment"}],"author":[{"embeddable":true,"href":"https://vigoexperience.com/wp-json/wp/v2/users/1"}]}
     */




}
