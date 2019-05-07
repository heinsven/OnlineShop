package uestc.hxf.shop.bean;

import java.util.List;

public class ResultBeanData {
    /**
     * code : 200
     * msg : 请求成功
     */
    private int code;
    private String msg;
    private ResultBean result;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public static class ResultBean {

        private SeckillInfoEntity seckill_info;
        private List<ActInfoEntity> act_info;
        private List<BannerInfoEntity> banner_info;
        private List<ChannelInfoEntity> channel_info;
        private List<HotInfoEntity> hot_info;
        private List<RecommendInfoEntity> recommend_info;

        public void setSeckill_info(SeckillInfoEntity seckill_info) {
            this.seckill_info = seckill_info;
        }

        public void setAct_info(List<ActInfoEntity> act_info) {
            this.act_info = act_info;
        }

        public void setBanner_info(List<BannerInfoEntity> banner_info) {
            this.banner_info = banner_info;
        }

        public void setChannel_info(List<ChannelInfoEntity> channel_info) {
            this.channel_info = channel_info;
        }

        public void setHot_info(List<HotInfoEntity> hot_info) {
            this.hot_info = hot_info;
        }

        public void setRecommend_info(List<RecommendInfoEntity> recommend_info) {
            this.recommend_info = recommend_info;
        }

        public SeckillInfoEntity getSeckill_info() {
            return seckill_info;
        }

        public List<ActInfoEntity> getAct_info() {
            return act_info;
        }

        public List<BannerInfoEntity> getBanner_info() {
            return banner_info;
        }

        public List<ChannelInfoEntity> getChannel_info() {
            return channel_info;
        }

        public List<HotInfoEntity> getHot_info() {
            return hot_info;
        }

        public List<RecommendInfoEntity> getRecommend_info() {
            return recommend_info;
        }

        public static class SeckillInfoEntity {

            private String end_time;
            private String start_time;
            private List<ListEntity> list;

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public void setList(List<ListEntity> list) {
                this.list = list;
            }

            public String getEnd_time() {
                return end_time;
            }

            public String getStart_time() {
                return start_time;
            }

            public List<ListEntity> getList() {
                return list;
            }

            public static class ListEntity {

                private String cover_price;
                private String figure;
                private String name;
                private String origin_price;
                private String product_id;

                public void setCover_price(String cover_price) {
                    this.cover_price = cover_price;
                }

                public void setFigure(String figure) {
                    this.figure = figure;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setOrigin_price(String origin_price) {
                    this.origin_price = origin_price;
                }

                public void setProduct_id(String product_id) {
                    this.product_id = product_id;
                }

                public String getCover_price() {
                    return cover_price;
                }

                public String getFigure() {
                    return figure;
                }

                public String getName() {
                    return name;
                }

                public String getOrigin_price() {
                    return origin_price;
                }

                public String getProduct_id() {
                    return product_id;
                }
            }
        }

        public static class ActInfoEntity {

            private String icon_url;
            private String name;
            private String url;

            public void setIcon_url(String icon_url) {
                this.icon_url = icon_url;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getIcon_url() {
                return icon_url;
            }

            public String getName() {
                return name;
            }

            public String getUrl() {
                return url;
            }
        }

        public static class BannerInfoEntity {

            private String image;
            private int option;
            private int type;
            private ValueEntity value;

            public void setImage(String image) {
                this.image = image;
            }

            public void setOption(int option) {
                this.option = option;
            }

            public void setType(int type) {
                this.type = type;
            }

            public void setValue(ValueEntity value) {
                this.value = value;
            }

            public String getImage() {
                return image;
            }

            public int getOption() {
                return option;
            }

            public int getType() {
                return type;
            }

            public ValueEntity getValue() {
                return value;
            }

            public static class ValueEntity {
                /**
                 * url : /act20161111?cyc_app=1
                 */

                private String url;

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getUrl() {
                    return url;
                }
            }
        }

        public static class ChannelInfoEntity {
            /**
             * channel_name : 服饰
             * image : /app/img/menu-cyc.png
             * option : 2
             * type : 1
             * value : {"channel_id":"8"}
             */

            private String channel_name;
            private String image;
            private int option;
            private int type;
            private ValueEntity value;

            public void setChannel_name(String channel_name) {
                this.channel_name = channel_name;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setOption(int option) {
                this.option = option;
            }

            public void setType(int type) {
                this.type = type;
            }

            public void setValue(ValueEntity value) {
                this.value = value;
            }

            public String getChannel_name() {
                return channel_name;
            }

            public String getImage() {
                return image;
            }

            public int getOption() {
                return option;
            }

            public int getType() {
                return type;
            }

            public ValueEntity getValue() {
                return value;
            }

            public static class ValueEntity {
                /**
                 * channel_id : 8
                 */

                private String channel_id;

                public void setChannel_id(String channel_id) {
                    this.channel_id = channel_id;
                }

                public String getChannel_id() {
                    return channel_id;
                }
            }
        }

        public static class HotInfoEntity {
            /**
             * cover_price : 159.00
             * figure : /1477984921265.jpg
             * name : 现货【一方尘寰】剑侠情缘三剑三七秀 干将莫邪 90橙武仿烧蓝复古对簪
             * product_id : 9356
             */

            private String cover_price;
            private String figure;
            private String name;
            private String product_id;

            public void setCover_price(String cover_price) {
                this.cover_price = cover_price;
            }

            public void setFigure(String figure) {
                this.figure = figure;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getCover_price() {
                return cover_price;
            }

            public String getFigure() {
                return figure;
            }

            public String getName() {
                return name;
            }

            public String getProduct_id() {
                return product_id;
            }
        }

        public static class RecommendInfoEntity {

            private String cover_price;
            private String figure;
            private String name;
            private String product_id;

            public void setCover_price(String cover_price) {
                this.cover_price = cover_price;
            }

            public void setFigure(String figure) {
                this.figure = figure;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getCover_price() {
                return cover_price;
            }

            public String getFigure() {
                return figure;
            }

            public String getName() {
                return name;
            }

            public String getProduct_id() {
                return product_id;
            }
        }
    }
}
