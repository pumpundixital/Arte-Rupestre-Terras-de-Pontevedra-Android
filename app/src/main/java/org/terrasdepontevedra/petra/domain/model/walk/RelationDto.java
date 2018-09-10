package org.terrasdepontevedra.petra.domain.model.walk;

import java.io.Serializable;
import java.util.List;

public class RelationDto implements Serializable{


    private int id;
    private String date;
    private String date_gmt;
    private GuidBean guid;
    private String modified;
    private String modified_gmt;
    private String slug;
    private String status;
    private String type;
    private String link;
    private TitleBean title;
    private ContentBean content;
    private String template;
    private RestApiEnablerBean rest_api_enabler;
    private LinksBean _links;
    private List<String> _wpcf_belongs_itinerario_id;
    private List<String> _wpcf_belongs_lugar_id;
    @com.google.gson.annotations.SerializedName("wpcf-orden")
    private List<String> wpcforden;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_gmt() {
        return date_gmt;
    }

    public void setDate_gmt(String date_gmt) {
        this.date_gmt = date_gmt;
    }

    public GuidBean getGuid() {
        return guid;
    }

    public void setGuid(GuidBean guid) {
        this.guid = guid;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getModified_gmt() {
        return modified_gmt;
    }

    public void setModified_gmt(String modified_gmt) {
        this.modified_gmt = modified_gmt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public TitleBean getTitle() {
        return title;
    }

    public void setTitle(TitleBean title) {
        this.title = title;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public RestApiEnablerBean getRest_api_enabler() {
        return rest_api_enabler;
    }

    public void setRest_api_enabler(RestApiEnablerBean rest_api_enabler) {
        this.rest_api_enabler = rest_api_enabler;
    }

    public LinksBean get_links() {
        return _links;
    }

    public void set_links(LinksBean _links) {
        this._links = _links;
    }

    public List<String> get_wpcf_belongs_itinerario_id() {
        return _wpcf_belongs_itinerario_id;
    }

    public void set_wpcf_belongs_itinerario_id(List<String> _wpcf_belongs_itinerario_id) {
        this._wpcf_belongs_itinerario_id = _wpcf_belongs_itinerario_id;
    }

    public List<String> get_wpcf_belongs_lugar_id() {
        return _wpcf_belongs_lugar_id;
    }

    public void set_wpcf_belongs_lugar_id(List<String> _wpcf_belongs_lugar_id) {
        this._wpcf_belongs_lugar_id = _wpcf_belongs_lugar_id;
    }

    public List<String> getWpcforden() {
        return wpcforden;
    }

    public void setWpcforden(List<String> wpcforden) {
        this.wpcforden = wpcforden;
    }

    public static class GuidBean {
        /**
         * rendered : https://vigoexperience.com/?post_type=relacion&#038;p=252
         */

        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }
    }

    public static class TitleBean {
        /**
         * rendered : relacion 252
         */

        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }
    }

    public static class ContentBean {
        /**
         * rendered :
         * protected : false
         */

        private String rendered;
        @com.google.gson.annotations.SerializedName("protected")
        private boolean protectedX;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }

        public boolean isProtectedX() {
            return protectedX;
        }

        public void setProtectedX(boolean protectedX) {
            this.protectedX = protectedX;
        }
    }

    public static class RestApiEnablerBean {
        /**
         * _wpcf_belongs_itinerario_id : 316
         * _wpcf_belongs_lugar_id : 299
         * wpcf-orden : 6
         */

        private String _wpcf_belongs_itinerario_id;
        private String _wpcf_belongs_lugar_id;
        @com.google.gson.annotations.SerializedName("wpcf-orden")
        private String wpcforden;

        public String get_wpcf_belongs_itinerario_id() {
            return _wpcf_belongs_itinerario_id;
        }

        public void set_wpcf_belongs_itinerario_id(String _wpcf_belongs_itinerario_id) {
            this._wpcf_belongs_itinerario_id = _wpcf_belongs_itinerario_id;
        }

        public String get_wpcf_belongs_lugar_id() {
            return _wpcf_belongs_lugar_id;
        }

        public void set_wpcf_belongs_lugar_id(String _wpcf_belongs_lugar_id) {
            this._wpcf_belongs_lugar_id = _wpcf_belongs_lugar_id;
        }

        public String getWpcforden() {
            return wpcforden;
        }

        public void setWpcforden(String wpcforden) {
            this.wpcforden = wpcforden;
        }
    }

    // FIXME generate failure  field _$WpAttachment285
    public static class LinksBean {
        private List<SelfBean> self;
        private List<CollectionBean> collection;
        private List<AboutBean> about;
        private List<CuriesBean> curies;

        public List<SelfBean> getSelf() {
            return self;
        }

        public void setSelf(List<SelfBean> self) {
            this.self = self;
        }

        public List<CollectionBean> getCollection() {
            return collection;
        }

        public void setCollection(List<CollectionBean> collection) {
            this.collection = collection;
        }

        public List<AboutBean> getAbout() {
            return about;
        }

        public void setAbout(List<AboutBean> about) {
            this.about = about;
        }

        public static class SelfBean {
            /**
             * href : https://vigoexperience.com/wp-json/wp/v2/relacion/252
             */

            private String href;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }

        public static class CollectionBean {
            /**
             * href : https://vigoexperience.com/wp-json/wp/v2/relacion
             */

            private String href;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }

        public static class AboutBean {
            /**
             * href : https://vigoexperience.com/wp-json/wp/v2/types/relacion
             */

            private String href;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }


        public static class CuriesBean {
            /**
             * name : wp
             * href : https://api.w.org/{rel}
             * templated : true
             */

            private String name;
            private String href;
            private boolean templated;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public boolean isTemplated() {
                return templated;
            }

            public void setTemplated(boolean templated) {
                this.templated = templated;
            }
        }
    }
}
