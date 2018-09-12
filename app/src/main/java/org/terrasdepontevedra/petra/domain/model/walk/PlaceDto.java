package org.terrasdepontevedra.petra.domain.model.walk;

import java.io.Serializable;
import java.util.List;

public class PlaceDto implements Serializable {

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
    private ExcerptBean excerpt;
    private int author;
    private int featured_media;
    private String template;
    private BetterFeaturedImageBean better_featured_image;
    private RestApiEnablerBean rest_api_enabler;
    private LinksBean _links;
    private List<?> meta;
    @com.google.gson.annotations.SerializedName("tipo-de-lugar")
    private List<Integer> tipodelugar;
    @com.google.gson.annotations.SerializedName("wpcf-localizacion-lugar")
    private List<String> wpcflocalizacionlugar;
    @com.google.gson.annotations.SerializedName("wpcf-telefono-lugar")
    private List<String> wpcftelefonolugar;
    @com.google.gson.annotations.SerializedName("wpcf-web-lugar")
    private List<String> wpcfweblugar;
    private List<String> latitude;
    private List<String> longitude;
    @com.google.gson.annotations.SerializedName("wpcf-imagen-adicional-lugar")
    private List<String> images;


    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDate_gmt() {
        return date_gmt;
    }

    public GuidBean getGuid() {
        return guid;
    }

    public String getModified() {
        return modified;
    }

    public String getModified_gmt() {
        return modified_gmt;
    }

    public String getSlug() {
        return slug;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public TitleBean getTitle() {
        return title;
    }

    public ContentBean getContent() {
        return content;
    }

    public ExcerptBean getExcerpt() {
        return excerpt;
    }

    public int getAuthor() {
        return author;
    }

    public int getFeatured_media() {
        return featured_media;
    }

    public String getTemplate() {
        return template;
    }

    public BetterFeaturedImageBean getBetter_featured_image() {
        return better_featured_image;
    }

    public RestApiEnablerBean getRest_api_enabler() {
        return rest_api_enabler;
    }

    public LinksBean get_links() {
        return _links;
    }

    public List<?> getMeta() {
        return meta;
    }

    public List<Integer> getTipodelugar() {
        return tipodelugar;
    }

    public List<String> getWpcflocalizacionlugar() {
        return wpcflocalizacionlugar;
    }

    public List<String> getWpcftelefonolugar() {
        return wpcftelefonolugar;
    }

    public List<String> getWpcfweblugar() {
        return wpcfweblugar;
    }

    public List<String> getLatitude() {
        return latitude;
    }

    public List<String> getLongitude() {
        return longitude;
    }

    public List<String> getImages() {
        return images;
    }

    public static class GuidBean {


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
         * rendered : Dinoseto
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
         * rendered : <p>El <b>Dinoseto</b> es un arbusto con forma de dinosaurio. Su primera ubicación fue en la calle calle Rosalía de Castro de la ciudad debido a una equivocación de la empresa encargada de su instalación. Sin embargo, el <i>Dinoseto</i> se convirtió de inmediato en un éxito en las redes sociales, lo que provocó que se ubicara en la Puerta del Sol. Se ha convertido en una de las grandes atracciones turísticas de la ciudad, convirtiéndose en tradición que los turistas se realicen un selfie en la plataforma que se se encuentra situada al lado de la escultura.</p>
         * <p>Como consecuencia de su popularidad, el Ayuntamiento de Vigo ha colocado nuevos setos con forma de dinosaurio, como es el caso del <i>Rinoseto</i> (con forma de triceratops) en Navia o el del <i>Dinosetiño</i>, situado al lado del <i>Dinoseto</i> original en la Puerta del Sol de la ciudad.</p>
         * <p>
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

    public static class ExcerptBean {
        /**
         * rendered : <p>¡Hazte un selfie con el dinoseto!</p>
         * <p>
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

    public static class BetterFeaturedImageBean {

        private int id;
        private String alt_text;
        private String caption;
        private String description;
        private String media_type;
        private MediaDetailsBean media_details;
        private int post;
        private String source_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAlt_text() {
            return alt_text;
        }

        public void setAlt_text(String alt_text) {
            this.alt_text = alt_text;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMedia_type() {
            return media_type;
        }

        public void setMedia_type(String media_type) {
            this.media_type = media_type;
        }

        public MediaDetailsBean getMedia_details() {
            return media_details;
        }

        public void setMedia_details(MediaDetailsBean media_details) {
            this.media_details = media_details;
        }

        public int getPost() {
            return post;
        }

        public void setPost(int post) {
            this.post = post;
        }

        public String getSource_url() {
            return source_url;
        }

        public void setSource_url(String source_url) {
            this.source_url = source_url;
        }

        public static class MediaDetailsBean {


            private int width;
            private int height;
            private String file;
            private SizesBean sizes;
            private ImageMetaBean image_meta;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getFile() {
                return file;
            }

            public void setFile(String file) {
                this.file = file;
            }

            public SizesBean getSizes() {
                return sizes;
            }

            public void setSizes(SizesBean sizes) {
                this.sizes = sizes;
            }

            public ImageMetaBean getImage_meta() {
                return image_meta;
            }

            public void setImage_meta(ImageMetaBean image_meta) {
                this.image_meta = image_meta;
            }

            public static class SizesBean {

                private ThumbnailBean thumbnail;
                private MediumBean medium;
                private MediumLargeBean medium_large;
                private WidgetBean widget;
                private SquareBean square;
                private FeaturedBean featured;
                private FeaturedLargeBean featured_large;
                private PortfolioBean portfolio;
                private PortfolioSmallBean portfolio_small;
                private GalleryBean gallery;
                private MagazineBean magazine;
                private MasonryBean masonry;
                private EntryWithSidebarBean entry_with_sidebar;
                private EntryWithoutSidebarBean entry_without_sidebar;
                private ShopThumbnailBean shop_thumbnail;
                private ShopCatalogBean shop_catalog;
                private ShopSingleBean shop_single;

                public ThumbnailBean getThumbnail() {
                    return thumbnail;
                }

                public void setThumbnail(ThumbnailBean thumbnail) {
                    this.thumbnail = thumbnail;
                }

                public MediumBean getMedium() {
                    return medium;
                }

                public void setMedium(MediumBean medium) {
                    this.medium = medium;
                }

                public MediumLargeBean getMedium_large() {
                    return medium_large;
                }

                public void setMedium_large(MediumLargeBean medium_large) {
                    this.medium_large = medium_large;
                }

                public WidgetBean getWidget() {
                    return widget;
                }

                public void setWidget(WidgetBean widget) {
                    this.widget = widget;
                }

                public SquareBean getSquare() {
                    return square;
                }

                public void setSquare(SquareBean square) {
                    this.square = square;
                }

                public FeaturedBean getFeatured() {
                    return featured;
                }

                public void setFeatured(FeaturedBean featured) {
                    this.featured = featured;
                }

                public FeaturedLargeBean getFeatured_large() {
                    return featured_large;
                }

                public void setFeatured_large(FeaturedLargeBean featured_large) {
                    this.featured_large = featured_large;
                }

                public PortfolioBean getPortfolio() {
                    return portfolio;
                }

                public void setPortfolio(PortfolioBean portfolio) {
                    this.portfolio = portfolio;
                }

                public PortfolioSmallBean getPortfolio_small() {
                    return portfolio_small;
                }

                public void setPortfolio_small(PortfolioSmallBean portfolio_small) {
                    this.portfolio_small = portfolio_small;
                }

                public GalleryBean getGallery() {
                    return gallery;
                }

                public void setGallery(GalleryBean gallery) {
                    this.gallery = gallery;
                }

                public MagazineBean getMagazine() {
                    return magazine;
                }

                public void setMagazine(MagazineBean magazine) {
                    this.magazine = magazine;
                }

                public MasonryBean getMasonry() {
                    return masonry;
                }

                public void setMasonry(MasonryBean masonry) {
                    this.masonry = masonry;
                }

                public EntryWithSidebarBean getEntry_with_sidebar() {
                    return entry_with_sidebar;
                }

                public void setEntry_with_sidebar(EntryWithSidebarBean entry_with_sidebar) {
                    this.entry_with_sidebar = entry_with_sidebar;
                }

                public EntryWithoutSidebarBean getEntry_without_sidebar() {
                    return entry_without_sidebar;
                }

                public void setEntry_without_sidebar(EntryWithoutSidebarBean entry_without_sidebar) {
                    this.entry_without_sidebar = entry_without_sidebar;
                }

                public ShopThumbnailBean getShop_thumbnail() {
                    return shop_thumbnail;
                }

                public void setShop_thumbnail(ShopThumbnailBean shop_thumbnail) {
                    this.shop_thumbnail = shop_thumbnail;
                }

                public ShopCatalogBean getShop_catalog() {
                    return shop_catalog;
                }

                public void setShop_catalog(ShopCatalogBean shop_catalog) {
                    this.shop_catalog = shop_catalog;
                }

                public ShopSingleBean getShop_single() {
                    return shop_single;
                }

                public void setShop_single(ShopSingleBean shop_single) {
                    this.shop_single = shop_single;
                }

                public static class ThumbnailBean {


                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class MediumBean {

                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class MediumLargeBean {

                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class WidgetBean {



                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class SquareBean {


                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class FeaturedBean {


                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class FeaturedLargeBean {


                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class PortfolioBean {

                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class PortfolioSmallBean {


                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class GalleryBean {


                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class MagazineBean {

                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class MasonryBean {


                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class EntryWithSidebarBean {


                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class EntryWithoutSidebarBean {

                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class ShopThumbnailBean {

                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class ShopCatalogBean {

                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class ShopSingleBean {

                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }
            }

            public static class ImageMetaBean {
                /**
                 * aperture : 0
                 * credit :
                 * camera :
                 * caption :
                 * created_timestamp : 0
                 * copyright :
                 * focal_length : 0
                 * iso : 0
                 * shutter_speed : 0
                 * title :
                 * orientation : 0
                 * keywords : []
                 */

                private String aperture;
                private String credit;
                private String camera;
                private String caption;
                private String created_timestamp;
                private String copyright;
                private String focal_length;
                private String iso;
                private String shutter_speed;
                private String title;
                private String orientation;
                private List<?> keywords;

                public String getAperture() {
                    return aperture;
                }

                public void setAperture(String aperture) {
                    this.aperture = aperture;
                }

                public String getCredit() {
                    return credit;
                }

                public void setCredit(String credit) {
                    this.credit = credit;
                }

                public String getCamera() {
                    return camera;
                }

                public void setCamera(String camera) {
                    this.camera = camera;
                }

                public String getCaption() {
                    return caption;
                }

                public void setCaption(String caption) {
                    this.caption = caption;
                }

                public String getCreated_timestamp() {
                    return created_timestamp;
                }

                public void setCreated_timestamp(String created_timestamp) {
                    this.created_timestamp = created_timestamp;
                }

                public String getCopyright() {
                    return copyright;
                }

                public void setCopyright(String copyright) {
                    this.copyright = copyright;
                }

                public String getFocal_length() {
                    return focal_length;
                }

                public void setFocal_length(String focal_length) {
                    this.focal_length = focal_length;
                }

                public String getIso() {
                    return iso;
                }

                public void setIso(String iso) {
                    this.iso = iso;
                }

                public String getShutter_speed() {
                    return shutter_speed;
                }

                public void setShutter_speed(String shutter_speed) {
                    this.shutter_speed = shutter_speed;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getOrientation() {
                    return orientation;
                }

                public void setOrientation(String orientation) {
                    this.orientation = orientation;
                }

                public List<?> getKeywords() {
                    return keywords;
                }

                public void setKeywords(List<?> keywords) {
                    this.keywords = keywords;
                }
            }
        }
    }

    public static class RestApiEnablerBean {
        /**
         * wpcf-localizacion-lugar : {42.23812427437025,-8.725622690807313}
         */

        @com.google.gson.annotations.SerializedName("wpcf-localizacion-lugar")
        private String wpcflocalizacionlugar;

        public String getWpcflocalizacionlugar() {
            return wpcflocalizacionlugar;
        }

        public void setWpcflocalizacionlugar(String wpcflocalizacionlugar) {
            this.wpcflocalizacionlugar = wpcflocalizacionlugar;
        }
    }

    // FIXME generate failure  field _$WpTerm19
// FIXME generate failure  field _$WpAttachment111
// FIXME generate failure  field _$WpFeaturedmedia2
    public static class LinksBean {
        private List<SelfBean> self;
        private List<CollectionBean> collection;
        private List<AboutBean> about;
        private List<AuthorBean> author;
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

        public List<AuthorBean> getAuthor() {
            return author;
        }

        public void setAuthor(List<AuthorBean> author) {
            this.author = author;
        }

        public static class SelfBean {


            private String href;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }

        public static class CollectionBean {


            private String href;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }

        public static class AboutBean {


            private String href;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }

        public static class AuthorBean {


            private boolean embeddable;
            private String href;

            public boolean isEmbeddable() {
                return embeddable;
            }

            public void setEmbeddable(boolean embeddable) {
                this.embeddable = embeddable;
            }

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