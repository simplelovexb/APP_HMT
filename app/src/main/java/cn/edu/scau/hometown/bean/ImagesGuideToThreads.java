package cn.edu.scau.hometown.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/9/29 0029.
 */
public class ImagesGuideToThreads implements Serializable{

    /**
     * images : [{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/29/131737cnr6tgrgrgjjccqq.jpg","tid":"817212","subject":"[生活大爆炸第九季][集数2][部分没字幕][800M][度盘]"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/29/104233hgjfwq0upulzchhk.jpg","tid":"817154","subject":"你好，你有一份来自有米的offer未领取"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/29/092859aa7sn4dyy49iysa2.png","tid":"817124","subject":"10月13日 下午14:40 华山学生活动中心107 钰诚集团2016校园招聘--华农站"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/28/230719prjscapz7sdlrloh.jpg","tid":"817087","subject":"谢谢你们的陪伴（记中秋假期）"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/28/183616d5ji6jjzy7jqyyvd.jpg","tid":"817051","subject":"送送送！手机没了，送lumia638绿色后壳，要的来取"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/29/162240wggxls5qzo05c54s.png","tid":"817048","subject":"【实习生招聘】珠江新城双休，广州市律中投资顾问有限公司"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/28/175247brgg53gr9yqxrr55.jpg","tid":"817046","subject":"[港囧][喜剧][中文字幕][TC/1080P-2/6.7G][种子]"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/28/160013xdk9d94tw6pd6asc.jpg","tid":"817014","subject":"大二大三准备考研的同学们，保研师姐这里有资料白菜出售！！"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/28/123927e4kk8ett504c3n53.png","tid":"816915","subject":"广东省国土资源测绘局大地队开发小组实习生招聘"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/28/092531mii1sss5e531vc5l.jpg","tid":"816810","subject":"史赛克2016校园招聘"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/28/084842a7pflzkq030zp2l7.jpg","tid":"816799","subject":"彩虹团队怎么加入？修正药业集团美添白如何代理？"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/27/172212mi8i3yu1y1yz3u83.png","tid":"816732","subject":"华图在线APP-全国最专业的3+2移动学习平台，一个平台，囊括IM、交友、听课、看书、..."},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/28/190954iblssv72m00x77s3.jpg","tid":"816683","subject":"【已出】大四师姐出售单车"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/26/225503qj66iiueqcefmi46.jpg","tid":"816580","subject":"[像素大战/Pixels][喜剧/动作/科幻][中文字幕][720P/1.14G][迅雷/度盘]"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/26/114408p18321l7j4fk4x24.jpg","tid":"816433","subject":"卖轮滑啦啦啦。。。"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/25/174203xcqscw1ccblk33rw.jpg","tid":"816215","subject":"钰诚集团2016校园宣讲---华农站   10月13日  下午14:40   华山学生活动中心107"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/25/173925x9nr6oxov573y415.jpg","tid":"816214","subject":"同学，有你的顺丰快递！请到中山大学体育馆签收！"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/25/174022sey5rwywiq32qyn3.jpg","tid":"816213","subject":"梦想扬帆 甜蜜护航----徐福记2016校园招聘正式启动"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/25/163239n1n3597r1rnlo15o.png","tid":"816187","subject":"10月13日 下午14:40  华山学生活动中心107 钰诚集团2016校园招聘-华农站"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/25/161215c5sc2rjpzwaau2mg.jpg","tid":"816179","subject":"恺英网络\u201c不要怂·就是干\u201d2016 届校园招聘"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/25/154944l0hlno0dz2k10qlm.png","tid":"816170","subject":"10月13日 15:00  华山学生活动中心（新学活） 钰诚集团2016校招\u2014\u2014华农站"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/25/113030qoiqoz5mdtg5tori.jpg","tid":"816062","subject":"【碧业生·五周年】  碧业生，我要说"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/25/105140ixew3m2m3yxl9eyg.jpg","tid":"816040","subject":"师姐回来招兼职啦·"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/25/023254r720bnsrwsi00q00.jpg","tid":"815965","subject":"急聘UI设计师（应届毕业生，月薪5K，包吃住）"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/24/231028g9hq0ev3g0e79n50.jpeg","tid":"815963","subject":"【2015毕业季の职场交流】老人来手把手调教你修改简历活动专帖"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/24/180639i0umg11p1vnfucb1.png","tid":"815912","subject":"广物地产集团2016届校园招聘"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/24/123323wx5zwfju2hoxbflo.jpg","tid":"815766","subject":"【转租.天河长湴.乐意居】主卧-三房两厅-安全舒适小区环境-交通便利(男女不限)"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/24/112917xng9yh37p3nm09nq.jpg","tid":"815740","subject":"毕业狗记忆中的华农"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/24/091713x174zv55i7hkr4r4.png","tid":"815657","subject":"我们仍未知道那天所看见的花的名字真人版SP"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/24/094200t1ppffnmf3xxopxr.jpg","tid":"815650","subject":"[碟中谍5神秘国度/Rogue.Nation][动作/冒险][双语字幕][720/1080P-3.9/4.8G][磁力]"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/225742cuncdfn3bddnj2dh.jpg","tid":"815615","subject":"谁家的猫猫走丢了！速来认领！！！！！"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/213604c5m5pl0non3bl2mf.jpg","tid":"815613","subject":"出售一些东西，有新的有旧的"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/225915kv56653c6zzxl5ek.jpg","tid":"815610","subject":"认真系列\u2014\u20142015年夏季番音乐推荐"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/183500shrkjrbepen372pr.jpg","tid":"815568","subject":"中国电信广东公司2016年校园招聘火热启动！"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/182652ia3j4yzz8e4eksss.jpg","tid":"815566","subject":"大四师姐甩卖单车o(∩_∩)o"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/162908b1chlino1p8cpzh1.jpg","tid":"815513","subject":"推荐一部蛮不错的犯罪题材传记剧集《毒枭》"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/154329ls8kneg4b4xq18b4.png","tid":"815494","subject":"诺禾致源生物2016校园招聘"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/152459svif736lzvvlfclt.jpg","tid":"815484","subject":"因公司发展需要，现特向社会招聘工作人员"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/143148pyoqozmur8bzuxxc.jpg","tid":"815457","subject":"谁都知道报安顺驾校非常看人品的"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/131848scl0lhhoz7zh0w1j.jpg","tid":"815429","subject":"173vpn网游加速器盘点LOL当前最火四大辅助"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/113944dstvxbtfsiss50bf.png","tid":"815392","subject":"珠海中航通航联合九天飞院半公费招飞简章"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/110636var0llmy5la3muza.png","tid":"815354","subject":"2016年菲音游戏校园招聘信息"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/093317uiic7gifhqezwiyg.jpg","tid":"815337","subject":"互联网+时代，你能不加入新闻部吗？︳招新"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/095558mx3sizld3w0pdd36.jpg","tid":"815297","subject":"梦想绽放华夏\u2014\u2014梦想舞台2015 总决赛 圆满落幕"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/23/094727yj2o1hss27ddvcc3.jpg","tid":"815294","subject":"梦想绽放华夏\u2014\u2014梦想舞台2015 总决赛暨颁奖典礼在港圆满落幕"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/22/162812o02aj0olyopjphv7.jpg","tid":"815196","subject":"[哈利波特1~7部全集][[Harry.Potter][剧情/爱情/动作/魔幻][720p][17.58G][度盘]"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/22/161239tcr1sywert8zsrsr.jpg","tid":"815149","subject":"寻找华农雨伞"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/22/102112gg3nqn4lblrzqqg5.png","tid":"814984","subject":"【2016校招】深信服科技校招（13万年薪起）"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/21/232726atbjdyjb9hsbbsqz.jpg","tid":"814899","subject":"华农大班服定制"},{"src":"http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/21/171600scucckk6meo2k0kr.png","tid":"814810","subject":"Hello  Kugou，\u201c音\u201d你多彩\u2014\u2014酷狗音乐2016校园招聘"}]
     * status : success
     */

    private String status;
    private List<ImagesEntity> images;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImages(List<ImagesEntity> images) {
        this.images = images;
    }

    public String getStatus() {
        return status;
    }

    public List<ImagesEntity> getImages() {
        return images;
    }

    public static class ImagesEntity implements Serializable{
        /**
         * src : http://hometown.scau.edu.cn/bbs/data/attachment/forum/201509/29/131737cnr6tgrgrgjjccqq.jpg
         * tid : 817212
         * subject : [生活大爆炸第九季][集数2][部分没字幕][800M][度盘]
         */

        private String src;
        private String tid;
        private String subject;

        public void setSrc(String src) {
            this.src = src;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getSrc() {
            return src;
        }

        public String getTid() {
            return tid;
        }

        public String getSubject() {
            return subject;
        }
    }
}
