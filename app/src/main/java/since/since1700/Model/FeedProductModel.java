package since.since1700.Model;

/**
 * Created by Kuppusamy on 9/26/2017.
 */

public class FeedProductModel {
     String producttitle,productimage,id,moreimagescount,producturl;
    int likescount,sharecount;

    public String getProducturl() {
        return producturl;
    }

    public void setProducturl(String producturl) {
        this.producturl = producturl;
    }

    public String getProducttitle() {
        return producttitle;
    }

    public void setProducttitle(String producttitle) {
        this.producttitle = producttitle;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getProductimage() {
        return productimage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLikescount() {
        return likescount;
    }

    public void setLikescount(int likescount) {
        this.likescount = likescount;
    }

    public int getSharecount() {
        return sharecount;
    }

    public void setSharecount(int sharecount) {
        this.sharecount = sharecount;
    }

    public String getMoreimagescount() {
        return moreimagescount;
    }

    public void setMoreimagescount(String moreimagescount) {
        this.moreimagescount = moreimagescount;
    }

}
