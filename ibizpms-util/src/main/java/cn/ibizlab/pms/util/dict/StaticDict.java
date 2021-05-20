package cn.ibizlab.pms.util.dict;

import lombok.Getter;

public class StaticDict {




    /**
     * 代码表[文档库权限]
     */
    @Getter
    public enum Doclib__acl {
        DEFAULT("default","默认"),
        CUSTOM("custom","自定义");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Doclib__acl(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[扩展表格单元格垂直对齐方式]
     */
    @Getter
    public enum CodeList75 {
        TOP("TOP","上对齐"),
        MIDDLE("MIDDLE","居中对齐"),
        BOTTOM("BOTTOM","下对齐");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList75(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[不需要评审]
     */
    @Getter
    public enum NeedNotReview {
        ITEM_0("0","不需要评审");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        NeedNotReview(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[数据库触发器目标]
     */
    @Getter
    public enum CodeList68 {
        TABLE("TABLE","主表"),
        VIEW("VIEW","视图");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList68(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[ZT_COMPANY__GUEST]
     */
    @Getter
    public enum Company__guest {
        ITEM_1("1","允许"),
        ITEM_0("0","不允许");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Company__guest(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[区块模块]
     */
    @Getter
    public enum Block__module {
        PRODUCT("product","产品"),
        PROJECT("project","项目"),
        QA("qa","测试"),
        TODO("todo","待办"),
        MY("my","我的地盘");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Block__module(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[Bug严重程度（Mob）]
     */
    @Getter
    public enum Bug__severity_mob {
        ITEM_1(1,"1"),
        ITEM_2(2,"2"),
        ITEM_3(3,"3"),
        ITEM_4(4,"4");

        private int value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Bug__severity_mob(int value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[审计行为]
     */
    @Getter
    public enum CodeList27 {
        CREATE("CREATE","建立"),
        UPDATE("UPDATE","更新"),
        DELETE("DELETE","删除");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList27(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[任务状态（查看）]
     */
    @Getter
    public enum TaskStatusCK {
        WAIT("wait","未开始"),
        DOING("doing","进行中"),
        DONE("done","已完成"),
        PAUSE("pause","已暂停"),
        CANCEL("cancel","已取消"),
        CLOSED("closed","已关闭"),
        STORYCHANGE("storychange","需求变更");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        TaskStatusCK(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试单快速分组]
     */
    @Getter
    public enum TestQuickpacket {
        ALL("ALL","全部"),
        ICREATE("ICREATE","待测测试单"),
        IREVIEW("IREVIEW","测试中测试单"),
        ICLOSE("ICLOSE","被阻塞测试单"),
        TESTED("Tested","已测测试单");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        TestQuickpacket(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[平台内置处理组件类型]
     */
    @Getter
    public enum CodeList118 {
        CODELISTFILLER("CODELISTFILLER","代码表填充器"),
        WFPROCESS("WFPROCESS","工作流嵌入处理"),
        DGACTIONHELPER("DGACTIONHELPER","表格后台处理对象"),
        FORMACTIONHELPER("FORMACTIONHELPER","表单后台处理类"),
        PAGE("PAGE","页面对象");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList118(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[模块类型]
     */
    @Getter
    public enum Module__type {
        LINE("line","产品线"),
        STORY("story","需求"),
        TASK("task","任务"),
        DOC("doc","文档目录"),
        CASE("case","测试用例"),
        BUG("bug","Bug");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Module__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[消息模板内容类型]
     */
    @Getter
    public enum CodeList42 {
        TEXT("TEXT","纯文本"),
        HTML("HTML","HTML网页");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList42(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[私人事务选择]
     */
    @Getter
    public enum Private_choose {
        ITEM_1("1","是");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Private_choose(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试用例工具/框架]
     */
    @Getter
    public enum Testcase__frame {
        JUNIT("junit","JUnit"),
        TESTNG("testng","TestNG"),
        PHPUNIT("phpunit","PHPUnit"),
        PYTEST("pytest","Pytest"),
        JTEST("jtest","JTest"),
        CPPUNIT("cppunit","CppUnit"),
        GTEST("gtest","GTest"),
        QTEST("qtest","QTest");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Testcase__frame(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[字段查询扩展选项]
     */
    @Getter
    public enum CodeList60 {
        LIKE("LIKE","LIKE大小写敏感"),
        EQ("=","=（含其它）大小写敏感"),
        LIKESPLIT("LIKESPLIT","LIKE分解");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList60(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[计划模板权限]
     */
    @Getter
    public enum PlanAcl {
        OPEN("open","公开"),
        PRIVATE("private","私有");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        PlanAcl(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[年份（2010～2020）]
     */
    @Getter
    public enum CodeList81 {
        ITEM_2010("2010","2010年"),
        ITEM_2011("2011","2011年"),
        ITEM_2012("2012","2012年"),
        ITEM_2013("2013","2013年"),
        ITEM_2014("2014","2014年"),
        ITEM_2015("2015","2015年"),
        ITEM_2016("2016","2016年"),
        ITEM_2017("2017","2017年"),
        ITEM_2018("2018","2018年"),
        ITEM_2019("2019","2019年"),
        ITEM_2020("2020","2020年"),
        ITEM_2021("2021","2021年"),
        ITEM_2022("2022","2022年"),
        ITEM_2023("2023","2023年"),
        ITEM_2024("2024","2024年"),
        ITEM_2025("2025","2025年"),
        ITEM_2026("2026","2026年"),
        ITEM_2027("2027","2027年"),
        ITEM_2028("2028","2028年");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList81(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[季度（1～4）]
     */
    @Getter
    public enum CodeList83 {
        ITEM_1("1","1季度"),
        ITEM_2("2","2季度"),
        ITEM_3("3","3季度"),
        ITEM_4("4","4季度");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList83(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[树视图节点类型]
     */
    @Getter
    public enum CodeList94 {
        STATIC("STATIC","静态"),
        DE("DE","动态（实体）"),
        CODELIST("CODELIST","动态（代码表）");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList94(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[项目优先级]
     */
    @Getter
    public enum Project__pri {
        ITEM_1("1","1"),
        ITEM_2("2","2"),
        ITEM_3("3","3"),
        ITEM_4("4","4");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Project__pri(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[指派用户-关闭]
     */
    @Getter
    public enum Assignedto_closed {
        CLOSED("closed","关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Assignedto_closed(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[燃尽图快速分组]
     */
    @Getter
    public enum BurnQuickpacket {
        EXWEEK("exWeek","去除周末"),
        WEEK("Week","显示周末");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        BurnQuickpacket(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[项目快速分组]
     */
    @Getter
    public enum ProjectQuickpacket {
        ALL("All","所有"),
        NOWAIT("nowait","未完成"),
        WAIT("wait","未开始"),
        DOING("doing","进行中"),
        SUSPENDED("suspended","已挂起"),
        CLOSED("closed","已关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        ProjectQuickpacket(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[管理现状]
     */
    @Getter
    public enum ConfigManagementstatus {
        PRODUCT_PROJECT("product_project","产品 - 项目"),
        PRODUCT_ITERATION("product_iteration","产品 - 迭代"),
        PROJECT_ITERATION("project_iteration","项目 - 迭代"),
        PRODUCT_SPRINT("product_sprint","产品 - 冲刺"),
        PROJECT_SPRINT("project_sprint","项目 - 冲刺");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        ConfigManagementstatus(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[任务关闭原因]
     */
    @Getter
    public enum Task__closed_reason {
        DONE("done","已完成"),
        CANCEL("cancel","已取消");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Task__closed_reason(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[补字应用场合类型]
     */
    @Getter
    public enum CodeList30 {
        GG("GG","公共"),
        SH("SH","审核"),
        PG("PG","派工"),
        SABTYY("SABTYY","SABTYY"),
        RKDSH("RKDSH","入库单审核"),
        CKDSH("CKDSH","出库单审核"),
        BSDSH("BSDSH","报损单审核"),
        SAOA("SAOA","SAOA"),
        FW("FW","发文"),
        SW("SW","收文");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList30(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[Bug快速分组]
     */
    @Getter
    public enum Bug__quickpacket {
        ALL("All","所有"),
        UNCLOSED("UNCLOSED","未关闭"),
        ICREATE("ICREATE","由我创建"),
        TOME("TOME","指派给我"),
        BYME("BYME","由我解决"),
        WAITCLOSED("WAITCLOSED","待关闭"),
        UNRESOLVED("UNRESOLVED","未解决"),
        MORE("MORE","更多"),
        UNCONFIRMED("UNCONFIRMED","未确认"),
        UNASSIGNED("UNASSIGNED","未指派"),
        EXPIREDBUG("EXPIREDBUG","过期Bug");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Bug__quickpacket(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[用例步骤类型]
     */
    @Getter
    public enum Casestep__type {
        STEP("step","步骤"),
        GROUP("group","分组"),
        ITEM("item","分组步骤");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Casestep__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[页面跳转处理_页面类型]
     */
    @Getter
    public enum CodeList113 {
        PAGE("PAGE","内置页面"),
        URL("URL","网页路径"),
        SCRIPT("SCRIPT","脚本");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList113(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[需求快速分组]
     */
    @Getter
    public enum Story__quickpacket {
        ALL("ALL","所有"),
        UNCLOSED("UNCLOSED","未关闭"),
        TOME("TOME","指给我"),
        ICREATE("ICREATE","我创建"),
        IREVIEW("IREVIEW","我评审"),
        DRAFT("DRAFT","草稿"),
        MORE("MORE","更多"),
        ICLOSE("ICLOSE","我关闭"),
        ACTIVED("ACTIVED","激活"),
        CHANGED("CHANGED","已变更"),
        TOBECLOSED("TOBECLOSED","待关闭"),
        CLOSED("CLOSED","已关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Story__quickpacket(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }




    /**
     * 代码表[ZT_USER__GENDER]
     */
    @Getter
    public enum User__gender {
        F("f","女"),
        M("m","男");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        User__gender(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[测试套件&用例库类型]
     */
    @Getter
    public enum Testsuite__type {
        LIBRARY("library","用例库"),
        PRIVATE("private","私有"),
        PUBLIC("public","公开");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Testsuite__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[ZT_ACTION__READ]
     */
    @Getter
    public enum Action__read {
        ITEM_0("0","0"),
        ITEM_1("1","1");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Action__read(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[运行模式]
     */
    @Getter
    public enum RELEASE_Type {
        DEPLOYPKG("DEPLOYPKG","部署系统组件到仓库"),
        PACKMOBAPP("PACKMOBAPP","打包移动应用"),
        PACKVER("PACKVER","打包版本"),
        PUBCODE("PUBCODE","代码发布"),
        PUBCODE2("PUBCODE2","代码发布（模板开发）"),
        STARTMSAPI("STARTMSAPI","启动微服务"),
        STARTMSAPP("STARTMSAPP","启动微服务应用"),
        STARTMSFUNC("STARTMSFUNC","启动微服务功能"),
        STARTX("STARTX","启动系统");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        RELEASE_Type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[数据操作步骤]
     */
    @Getter
    public enum CodeList11 {
        USERDECLARE("USERDECLARE","变量定义"),
        USERINIT("USERINIT","变量初始化"),
        INPUTCHECK("INPUTCHECK","数据检查"),
        BEFOREACTION("BEFOREACTION","操作之前"),
        EXECUTEACTION("EXECUTEACTION","执行操作"),
        AFTERACTION("AFTERACTION","操作之后");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList11(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[燃尽图间隔显示]
     */
    @Getter
    public enum Burninterval {
        ITEM_1("1","间隔1天"),
        ITEM_2("2","间隔2天"),
        ITEM_3("3","间隔3天"),
        ITEM_4("4","间隔4天"),
        ITEM_5("5","间隔5天"),
        ITEM_6("6","间隔6天"),
        ITEM_7("7","间隔7天"),
        ITEM_8("8","间隔8天"),
        ITEM_9("9","间隔9天"),
        ITEM_10("10","间隔10天"),
        ITEM_11("11","间隔11天");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Burninterval(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[代码发布路径]
     */
    @Getter
    public enum CodeList116 {
        DEFAULT("DEFAULT","默认");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList116(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试报告对象类型]
     */
    @Getter
    public enum Testreport__object_type {
        TESTTASK("testtask","测试单"),
        PROJECT("project","项目");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Testreport__object_type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[年周（1～52）]
     */
    @Getter
    public enum CodeList84 {
        ITEM_01("01","第1周"),
        ITEM_02("02","第2周"),
        ITEM_03("03","第3周"),
        ITEM_04("04","第4周"),
        ITEM_05("05","第5周"),
        ITEM_06("06","第6周"),
        ITEM_07("07","第7周"),
        ITEM_08("08","第8周"),
        ITEM_09("09","第9周"),
        ITEM_10("10","第10周"),
        ITEM_11("11","第11周"),
        ITEM_12("12","第12周"),
        ITEM_13("13","第13周"),
        ITEM_14("14","第14周"),
        ITEM_15("15","第15周"),
        ITEM_16("16","第16周"),
        ITEM_17("17","第17周"),
        ITEM_18("18","第18周"),
        ITEM_19("19","第19周"),
        ITEM_20("20","第20周"),
        ITEM_21("21","第21周"),
        ITEM_22("22","第22周"),
        ITEM_23("23","第23周"),
        ITEM_24("24","第24周"),
        ITEM_25("25","第25周"),
        ITEM_26("26","第26周"),
        ITEM_27("27","第27周"),
        ITEM_28("28","第28周"),
        ITEM_29("29","第29周"),
        ITEM_30("30","第30周"),
        ITEM_31("31","第31周"),
        ITEM_32("32","第32周"),
        ITEM_33("33","第33周"),
        ITEM_34("34","第34周"),
        ITEM_35("35","第35周"),
        ITEM_36("36","第36周"),
        ITEM_37("37","第37周"),
        ITEM_38("38","第38周"),
        ITEM_39("39","第39周"),
        ITEM_40("40","第40周"),
        ITEM_41("41","第41周"),
        ITEM_42("42","第42周"),
        ITEM_43("43","第43周"),
        ITEM_44("44","第44周"),
        ITEM_45("45","第45周"),
        ITEM_46("46","第46周"),
        ITEM_47("47","第47周"),
        ITEM_48("48","第48周"),
        ITEM_49("49","第49周"),
        ITEM_50("50","第50周"),
        ITEM_51("51","第51周"),
        ITEM_52("52","第52周");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList84(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[数据库触发器操作]
     */
    @Getter
    public enum CodeList65 {
        BEFORE("BEFORE","Before"),
        AFTER("AFTER","After"),
        INSTEADOF("INSTEADOF","Instead of");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList65(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }




    /**
     * 代码表[平台内置流程状态]
     */
    @Getter
    public enum WFStates {
        ITEM_0("0","未启动"),
        ITEM_1("1","流程中"),
        ITEM_2("2","已完成"),
        ITEM_3("3","已取消"),
        ITEM_31("31","已取消(人工)"),
        ITEM_32("32","已取消(超时)"),
        ITEM_4("4","处理故障");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        WFStates(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[表单嵌入表格工具栏能力]
     */
    @Getter
    public enum CodeList77 {
        INSERT("INSERT","新建"),
        UPDATE("UPDATE","更新"),
        DELETE("DELETE","删除"),
        ROWEDIT("ROWEDIT","启用行编辑");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList77(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[任务优先级]
     */
    @Getter
    public enum Task__pri {
        ITEM_1(1,"1"),
        ITEM_2(2,"2"),
        ITEM_3(3,"3"),
        ITEM_4(4,"4");

        private int value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Task__pri(int value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[功能测试快速分组]
     */
    @Getter
    public enum CaseQuickpachet {
        ALL("ALL","所有"),
        TOME("TOME","需求变动");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CaseQuickpachet(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[页面处理逻辑类型]
     */
    @Getter
    public enum CodeList70 {
        AFTERINITPAGEPARAM("AFTERINITPAGEPARAM","页面变量初始化之后");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList70(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[需求快速分组（我的地盘）]
     */
    @Getter
    public enum StoryQuickpacketMy {
        TOME("TOME","指给我"),
        ICREATE("ICREATE","由我创建"),
        IREVIEW("IREVIEW","由我评审"),
        ICLOSE("ICLOSE","由我关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        StoryQuickpacketMy(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[系统日志操作方式]
     */
    @Getter
    public enum ActionManner {
        STORY_CLOSED_WILLNOTDO("story_closed_willnotdo","不做"),
        STORY_CLOSED_CANCEL("story_closed_cancel","已取消"),
        STORY_CLOSED_DONE("story_closed_done","已完成"),
        STORY_CLOSED_SUBDIVIDED("story_closed_subdivided","已细分"),
        STORY_CLOSED_POSTPONED("story_closed_postponed","延期"),
        STORY_CLOSED_BYDESIGN("story_closed_bydesign","设计如此"),
        STORY_CLOSED_DUPLICATE("story_closed_duplicate","重复"),
        BUG_RESOLVED_WILLNOTFIX("bug_resolved_willnotfix","不予解决"),
        BUG_RESOLVED_EXTERNAL("bug_resolved_external","外部原因"),
        BUG_RESOLVED_FIXED("bug_resolved_fixed","已解决"),
        BUG_RESOLVED_POSTPONED("bug_resolved_postponed","延期处理"),
        BUG_RESOLVED_NOTREPRO("bug_resolved_notrepro","无法重现"),
        BUG_RESOLVED_BYDESIGN("bug_resolved_bydesign","设计如此"),
        BUG_RESOLVED_TOSTORY("bug_resolved_tostory","转为需求"),
        BUG_RESOLVED_DUPLICATE("bug_resolved_duplicate","重复Bug"),
        STORY_REVIEWED_REJECT("story_reviewed_reject","拒绝"),
        STORY_REVIEWED_REVERT("story_reviewed_revert","撤销变更"),
        STORY_REVIEWED_CLARIFY("story_reviewed_clarify","有待明确"),
        STORY_REVIEWED_PASS("story_reviewed_pass","确认通过"),
        _RELEASE_CHANGESTATUS_TERMINATE(" release_changestatus_terminate","停止维护"),
        _RELEASE_CHANGESTATUS_NORMAL(" release_changestatus_normal","正常");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        ActionManner(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[日期类型]
     */
    @Getter
    public enum CodeList48 {
        ITEM_1("1","每周"),
        ITEM_2("2","每月");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList48(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[系统日志对象类型]
     */
    @Getter
    public enum Action__object_type {
        PRODUCT("product","产品"),
        STORY("story","需求"),
        PRODUCTPLAN("productplan","计划"),
        RELEASE("release","发布"),
        PROJECT("project","项目"),
        TASK("task","任务"),
        BUILD("build","版本"),
        BUG("bug","Bug"),
        CASE("case","用例"),
        CASERESULT("caseresult","用例结果"),
        STEPRESULT("stepresult","用例步骤"),
        TESTTASK("testtask","测试单"),
        USER("user","用户"),
        DOC("doc","文档"),
        DOCLIB("doclib","文档库"),
        TODO("todo","待办"),
        BRANCH("branch","分支"),
        MODULE("module","模块"),
        TESTSUITE("testsuite","套件"),
        CASELIB("caselib","用例库"),
        TESTREPORT("testreport","报告"),
        ENTRY("entry","应用"),
        WEBHOOK("webhook","Webhook"),
        DAILY("daily","日报"),
        WEEKLY("weekly","周报"),
        MONTHLY("monthly","月报"),
        REPORTLY("reportly","汇报");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Action__object_type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[需求颜色]
     */
    @Getter
    public enum Story__color {
        ITEM_1("#3da7f5","#3da7f5"),
        ITEM_2("#75c941","#75c941"),
        ITEM_3("#2dbdb2","#2dbdb2"),
        ITEM_4("#797ec9","#797ec9"),
        ITEM_5("#ffaf38","#ffaf38"),
        ITEM_6("#ff4e3e","#ff4e3e");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Story__color(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[服务运行状态]
     */
    @Getter
    public enum CodeList38 {
        START("START","启动"),
        STOP("STOP","停止");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList38(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }




    /**
     * 代码表[生产体系类型]
     */
    @Getter
    public enum Ibzpro__pssystype {
        IBIZSYS6("iBizSys6","iBizSys6");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Ibzpro__pssystype(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[ZT_TODO__STATUS]
     */
    @Getter
    public enum Todo__status {
        WAIT("wait","未开始"),
        DOING("doing","进行中"),
        DONE("done","已完成"),
        CLOSED("closed","已关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Todo__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[需求状态]
     */
    @Getter
    public enum Story__status {
        DRAFT("draft","草稿"),
        ACTIVE("active","激活"),
        CLOSED("closed","已关闭"),
        CHANGED("changed","已变更");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Story__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[用户对象类型]
     */
    @Getter
    public enum CodeList5 {
        USER("USER","用户"),
        USERGROUP("USERGROUP","用户组");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList5(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[不需要评审（新建）]
     */
    @Getter
    public enum NeedNotReviewNew {
        ITEM_1("1","不需要评审");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        NeedNotReviewNew(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[DA日志操作类型]
     */
    @Getter
    public enum CodeList24 {
        CREATE("CREATE","新建"),
        UPDATE("UPDATE","更新"),
        DELETE("DELETE","删除");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList24(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[脚本功能]
     */
    @Getter
    public enum CodeList114 {
        ITEM_1("1","基础"),
        ITEM_128("128","树视图常规"),
        ITEM_2("2","树视图高级"),
        ITEM_4("4","TAB视图"),
        ITEM_8("8","动态面板"),
        ITEM_16("16","搜索面板"),
        ITEM_32("32","表格视图常规"),
        ITEM_64("64","表格视图高级"),
        ITEM_256("256","数据视图");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList114(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[实体快捷应用范围]
     */
    @Getter
    public enum CodeList41 {
        ITEM_1("1","拾取链接");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList41(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[项目访问控制]
     */
    @Getter
    public enum Project__acl {
        OPEN("open","默认设置(有项目视图权限，即可访问)"),
        PRIVATE("private","私有项目(只有项目团队成员才能访问)"),
        CUSTOM("custom","自定义白名单(团队成员和白名单的成员可以访问)");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Project__acl(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[是否（TRUE，FALSE）]
     */
    @Getter
    public enum TrueFalse {
        TRUE("TRUE","是"),
        FALSE("FALSE","否");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        TrueFalse(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[服务启动模式]
     */
    @Getter
    public enum CodeList37 {
        AUTO("AUTO","自动"),
        MANUAL("MANUAL","手动");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList37(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[实体规则处理_操作逻辑]
     */
    @Getter
    public enum CodeList111 {
        EQ("=","等于"),
        LTGT("<>","不等于"),
        GTEQ(">=","大于等于"),
        GT(">","大于"),
        LTEQ("<=","小于等于"),
        LT("<","小于"),
        LIKE("LIKE","文本匹配"),
        ISNULL("ISNULL","为空"),
        ISNOTNULL("ISNOTNULL","不为空");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList111(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[任务快速分组（我的地盘）]
     */
    @Getter
    public enum TASK_QuickacketMy {
        TOME("TOME","指派给我"),
        CANCELLED("CANCELLED","由我创建"),
        IFINISHED("IFINISHED","由我完成"),
        COMPLETED("COMPLETED","由我关闭"),
        CLOSED("CLOSED","由我取消");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        TASK_QuickacketMy(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[项目时间类型]
     */
    @Getter
    public enum ProjectTimeType {
        CONSUMED("CONSUMED","消耗时间"),
        LEFT("LEFT","剩余时间");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        ProjectTimeType(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }




    /**
     * 代码表[需求故事点]
     */
    @Getter
    public enum StoryPoints {
        ITEM_0("0","0"),
        ITEM_05("0.5","0.5"),
        ITEM_1("1","1"),
        ITEM_2("2","2"),
        ITEM_3("3","3"),
        ITEM_5("5","5"),
        ITEM_8("8","8"),
        ITEM_13("13","13"),
        ITEM_20("20","20"),
        ITEM_40("40","40"),
        ITEM_100("100","100");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        StoryPoints(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[任务状态]
     */
    @Getter
    public enum Task__status {
        WAIT("wait","未开始"),
        DOING("doing","进行中"),
        DONE("done","已完成"),
        PAUSE("pause","已暂停"),
        CANCEL("cancel","已取消"),
        CLOSED("closed","已关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Task__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试单优先级]
     */
    @Getter
    public enum Testtask__pri {
        ITEM_1(1,"1"),
        ITEM_2(2,"2"),
        ITEM_3(3,"3"),
        ITEM_4(4,"4");

        private int value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Testtask__pri(int value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[预定义属性类型]
     */
    @Getter
    public enum CodeList34 {
        LOGICVALID("LOGICVALID","逻辑有效标识"),
        CREATEMAN("CREATEMAN","建立人"),
        CREATEDATE("CREATEDATE","建立时间"),
        UPDATEMAN("UPDATEMAN","更新人"),
        UPDATEDATE("UPDATEDATE","更新时间"),
        ORGUNITID("ORGUNITID","组织单元标识"),
        ORGUNITNAME("ORGUNITNAME","组织单元名称");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList34(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[Bug快速分组（移动端）]
     */
    @Getter
    public enum MobBugQuickGroup {
        ALL("All","所有"),
        UNCLOSED("UNCLOSED","未关闭"),
        ICREATE("ICREATE","由我创建"),
        MORE("MORE","更多"),
        TOME("TOME","指派给我"),
        BYME("BYME","由我解决"),
        WAITCLOSED("WAITCLOSED","待关闭"),
        UNRESOLVED("UNRESOLVED","未解决"),
        UNCONFIRMED("UNCONFIRMED","未确认"),
        UNASSIGNED("UNASSIGNED","未指派"),
        EXPIREDBUG("EXPIREDBUG","过期Bug");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        MobBugQuickGroup(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[任务类型]
     */
    @Getter
    public enum Task__type {
        DESIGN("design","设计"),
        DEVEL("devel","开发"),
        TEST("test","测试"),
        STUDY("study","研究"),
        DISCUSS("discuss","讨论"),
        UI("ui","界面"),
        AFFAIR("affair","事务"),
        SERVE("serve","服务"),
        MISC("misc","其他");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Task__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[产品动态快速分组]
     */
    @Getter
    public enum ProductActionQuickpacket {
        ALL("All","所有"),
        TODAY("today","今天"),
        YESTERDAY("yesterday","昨天"),
        THISWEEK("thisweek","本周"),
        LASTWEEK("lastweek","上周"),
        THISMONTH("thismonth","本月"),
        LASTMONTH("lastmonth","上月");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        ProductActionQuickpacket(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[列编辑器样式]
     */
    @Getter
    public enum CodeList28 {
        DROPDOWNLIST("DROPDOWNLIST","下拉列表框"),
        PICKER("PICKER","数据选择框");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList28(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试单状态]
     */
    @Getter
    public enum Testtask__status {
        WAIT("wait","未开始"),
        DOING("doing","进行中"),
        DONE("done","已完成"),
        BLOCKED("blocked","被阻塞");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Testtask__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[数据通知_时间条件]
     */
    @Getter
    public enum CodeList62 {
        BEFORE("BEFORE","之前"),
        AFTER("AFTER","之后");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList62(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[网页部件类型]
     */
    @Getter
    public enum CodeList6 {
        CHART("CHART","图形部件"),
        LIST("LIST","列表"),
        CUSTOMWP("CUSTOMWP","自定义");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList6(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[星期（1～7）]
     */
    @Getter
    public enum CodeList46 {
        ITEM_2("2","星期一"),
        ITEM_3("3","星期二"),
        ITEM_4("4","星期三"),
        ITEM_5("5","星期四"),
        ITEM_6("6","星期五"),
        ITEM_7("7","星期六"),
        ITEM_1("1","星期日");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList46(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[DA日志对象类型]
     */
    @Getter
    public enum CodeList23 {
        DATAENTITY("DATAENTITY","实体"),
        DEFIELD("DEFIELD","实体属性"),
        DER1N("DER1N","实体关系");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList23(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[需求关闭原因]
     */
    @Getter
    public enum Story__closed_reason {
        DONE("done","已完成"),
        SUBDIVIDED("subdivided","已细分"),
        DUPLICATE("duplicate","重复"),
        POSTPONED("postponed","延期"),
        WILLNOTDO("willnotdo","不做"),
        CANCEL("cancel","已取消"),
        BYDESIGN("bydesign","设计如此");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Story__closed_reason(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[是否（YESNO）]
     */
    @Getter
    public enum YesNo3 {
        YES("yes","是"),
        NO("no","否");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        YesNo3(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[月周（1～5）]
     */
    @Getter
    public enum CodeList86 {
        ITEM_1("1","1周"),
        ITEM_2("2","2周"),
        ITEM_3("3","3周"),
        ITEM_4("4","4周"),
        ITEM_5("5","5周");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList86(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[系统日志操作类型]
     */
    @Getter
    public enum Action__type {
        CREATED("created","创建"),
        OPENED("opened","创建"),
        CHANGED("changed","变更了"),
        EDITED("edited","编辑了"),
        ASSIGNED("assigned","指派了"),
        CLOSED("closed","关闭了"),
        DELETED("deleted","删除了"),
        DELETEDFILE("deletedfile","删除附件"),
        EDITFILE("editfile","编辑附件"),
        ERASED("erased","删除了"),
        UNDELETED("undeleted","还原了"),
        HIDDEN("hidden","隐藏了"),
        COMMENTED("commented","评论了"),
        ACTIVATED("activated","激活了"),
        BLOCKED("blocked","阻塞了"),
        RESOLVED("resolved","解决了"),
        REVIEWED("reviewed","评审了"),
        MOVED("moved","移动了"),
        CONFIRMED("confirmed","确认了需求"),
        BUGCONFIRMED("bugconfirmed","确认了"),
        TOSTORY("tostory","转需求"),
        FROMBUG("frombug","转需求"),
        FROMLIB("fromlib","从用例库导入"),
        TOTASK("totask","转任务"),
        SVNCOMMITED("svncommited","提交代码"),
        GITCOMMITED("gitcommited","提交代码"),
        LINKED2PLAN("linked2plan","关联计划"),
        UNLINKEDFROMPLAN("unlinkedfromplan","移除计划"),
        CHANGESTATUS("changestatus","修改状态"),
        MARKED("marked","编辑了"),
        LINKED2PROJECT("linked2project","关联项目"),
        UNLINKEDFROMPROJECT("unlinkedfromproject","移除项目"),
        UNLINKEDFROMBUILD("unlinkedfrombuild","移除版本"),
        LINKED2RELEASE("linked2release","关联发布"),
        UNLINKEDFROMRELEASE("unlinkedfromrelease","移除发布"),
        LINKRELATEDBUG("linkrelatedbug","关联了相关Bug"),
        UNLINKRELATEDBUG("unlinkrelatedbug","移除了相关Bug"),
        LINKRELATEDCASE("linkrelatedcase","关联了相关用例"),
        UNLINKRELATEDCASE("unlinkrelatedcase","移除了相关用例"),
        LINKRELATEDSTORY("linkrelatedstory","关联了相关需求"),
        UNLINKRELATEDSTORY("unlinkrelatedstory","移除了相关需求"),
        SUBDIVIDESTORY("subdividestory","细分了需求"),
        UNLINKCHILDSTORY("unlinkchildstory","移除了细分需求"),
        UNLINKEDFROMBRANCH("unlinkedfrombranch","移除平台"),
        STARTED("started","开始了"),
        RESTARTED("restarted","继续了"),
        RECORDESTIMATE("recordestimate","记录了工时"),
        EDITESTIMATE("editestimate","编辑了工时"),
        CANCELED("canceled","取消了"),
        FINISHED("finished","完成了"),
        PAUSED("paused","暂停了"),
        VERIFIED("verified","验收了"),
        DELAYED("delayed","延期"),
        SUSPENDED("suspended","挂起"),
        LOGIN("login","登录系统"),
        LOGOUT("logout","退出登录"),
        DELETEESTIMATE("deleteestimate","删除了工时"),
        LINKED2BUILD("linked2build","关联了"),
        LINKED2BUG("linked2bug","关联了"),
        LINKCHILDTASK("linkchildtask","关联子任务"),
        UNLINKCHILDRENTASK("unlinkchildrentask","取消关联子任务"),
        LINKPARENTTASK("linkparenttask","关联到父任务"),
        UNLINKPARENTTASK("unlinkparenttask","从父任务取消关联"),
        BATCHCREATE("batchcreate","批量创建任务"),
        CREATECHILDREN("createchildren","创建子任务"),
        MANAGED("managed","维护"),
        DELETECHILDRENTASK("deletechildrentask","删除子任务"),
        CREATECHILDRENSTORY("createchildrenstory","创建子需求"),
        LINKCHILDSTORY("linkchildstory","关联子需求"),
        UNLINKCHILDRENSTORY("unlinkchildrenstory","取消关联子需求"),
        LINKPARENTSTORY("linkparentstory","关联到父需求"),
        UNLINKPARENTSTORY("unlinkparentstory","从父需求取消关联"),
        DELETECHILDRENSTORY("deletechildrenstory","删除子需求"),
        SUBMIT("submit","提交"),
        READ("read","已读了"),
        REMIND("remind","提醒"),
        UNLINKEDFROMBRANCH("unlinkedfrombranch","移除关联平台");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Action__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[数据库操作]
     */
    @Getter
    public enum CodeList89 {
        INSERT("INSERT","插入"),
        UPDATE("UPDATE","更新"),
        DELETE("DELETE","删除");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList89(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[需求阶段_看板]
     */
    @Getter
    public enum StoryStageKane {
        PROJECTED("projected","已立项"),
        DEVELOPING("developing","研发中"),
        DEVELOPED("developed","研发完毕"),
        TESTING("testing","测试中"),
        TESTED("tested","测试完毕"),
        VERIFIED("verified","已验收"),
        RELEASED("released","已发布");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        StoryStageKane(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试单快速分组（移动端）]
     */
    @Getter
    public enum MobTestQuickpacket {
        ALL("ALL","全部"),
        ICREATE("ICREATE","待测测试单"),
        IREVIEW("IREVIEW","测试中测试单"),
        MORE("MORE","更多"),
        ICLOSE("ICLOSE","被阻塞测试单"),
        TESTED("Tested","已测测试单");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        MobTestQuickpacket(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[实体属性更新模式]
     */
    @Getter
    public enum CodeList2 {
        VERSION("VERSION","版本模式");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList2(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[属性引用关系类型]
     */
    @Getter
    public enum CodeList90 {
        DER1N("DER1N","1:N关系"),
        DERCUSTOM("DERCUSTOM","自定义关系");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList90(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[ZT_IM_MESSAGE__TYPE]
     */
    @Getter
    public enum Im_message__type {
        NORMAL("normal","normal"),
        BROADCAST("broadcast","broadcast"),
        NOTIFY("notify","notify");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Im_message__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[ZT_IM_CLIENT__STATUS]
     */
    @Getter
    public enum Im_client__status {
        RELEASED("released","released"),
        WAIT("wait","wait");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Im_client__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[实体归属]
     */
    @Getter
    public enum CodeList19 {
        SRFDA("SRFDA","系统"),
        APPLICATION("APPLICATION","应用"),
        USER("USER","用户");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList19(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[DB2触发器代码模式]
     */
    @Getter
    public enum CodeList67 {
        FOR_EACH_ROW("For Each Row","For Each Row"),
        FOR_EACH_STATEMENT("For Each Statement","For Each Statement");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList67(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[任务颜色]
     */
    @Getter
    public enum Task__color {
        ITEM_1("#3da7f5","#3da7f5"),
        ITEM_2("#75c941","#75c941"),
        ITEM_3("#2dbdb2","#2dbdb2"),
        ITEM_4("#797ec9","#797ec9"),
        ITEM_5("#ffaf38","#ffaf38"),
        ITEM_6("#ff4e3e","#ff4e3e");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Task__color(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[功能类型]
     */
    @Getter
    public enum CodeList4 {
        DEDATAGRID("DEDATAGRID","默认实体表格视图"),
        PAGELINK("PAGELINK","页面链接"),
        JSCODE("JSCODE","纯JS代码"),
        DEGRIDVIEW("DEGRIDVIEW","指定实体表格视图"),
        PAGE("PAGE","内置页面");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList4(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试用例优先级]
     */
    @Getter
    public enum Testcase__pri {
        ITEM_1(1,"1"),
        ITEM_2(2,"2"),
        ITEM_3(3,"3"),
        ITEM_4(4,"4");

        private int value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Testcase__pri(int value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[产品状态]
     */
    @Getter
    public enum Product__status {
        NORMAL("normal","正常"),
        CLOSED("closed","结束");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Product__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[业务类别]
     */
    @Getter
    public enum Ibzpro__category {
        ZT_TODO("ZT_TODO","ZT_TODO");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Ibzpro__category(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[ZT_USER__CLIENTSTATUS]
     */
    @Getter
    public enum User__clientStatus {
        ONLINE("online","online"),
        AWAY("away","away"),
        BUSY("busy","busy"),
        OFFLINE("offline","offline");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        User__clientStatus(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[输入辅助_消息模板宏]
     */
    @Getter
    public enum CodeList102 {
        CARETTEMPLGROUP_SRFMSG_MSGTEMPLATE("CARETTEMPLGROUP_SRFMSG_MSGTEMPLATE","消息模板"),
        CARETTEMPLGROUP_SRFDA_FILLENTITYPARAM("CARETTEMPLGROUP_SRFDA_FILLENTITYPARAM","系统属性");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList102(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[ZT_IM_MESSAGESTATUS__STATUS]
     */
    @Getter
    public enum Im_messagestatus__status {
        WAITING("waiting","waiting"),
        SENT("sent","sent"),
        READED("readed","readed"),
        DELETED("deleted","deleted");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Im_messagestatus__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[代码表或模式]
     */
    @Getter
    public enum CodeList20 {
        NUMBERORMODE("NUMBERORMODE","数字或处理"),
        STRINGORMODE("STRINGORMODE","文本或模式");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList20(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[Bug颜色]
     */
    @Getter
    public enum Bug__color {
        ITEM_1("#3da7f5","#3da7f5"),
        ITEM_2("#75c941","#75c941"),
        ITEM_3("#2dbdb2","#2dbdb2"),
        ITEM_4("#797ec9","#797ec9"),
        ITEM_5("#ffaf38","#ffaf38"),
        ITEM_6("#ff4e3e","#ff4e3e");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Bug__color(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[是否（蓝、红）]
     */
    @Getter
    public enum CodeList50 {
        ITEM_1("1","是"),
        ITEM_0("0","否");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList50(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[表格列构建器]
     */
    @Getter
    public enum CodeList9 {
        NUMBER("NUMBER","数值"),
        CODELIST("CODELIST","代码表");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList9(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[用户模板类型]
     */
    @Getter
    public enum UserTplType {
        STORY("story","需求"),
        TASK("task","任务"),
        BUG("bug","Bug");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        UserTplType(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[项目状态(wait=doing)]
     */
    @Getter
    public enum Project_staus {
        DOING("doing","进行中"),
        SUSPENDED("suspended","已挂起"),
        CLOSED("closed","已关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Project_staus(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[来源区块模块]
     */
    @Getter
    public enum Block__source {
        PRODUCT("product","产品"),
        PROJECT("project","项目"),
        QA("qa","测试"),
        TODO("todo","待办");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Block__source(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[消息类型]
     */
    @Getter
    public enum CodeList29 {
        ITEM_1("1","系统消息"),
        ITEM_2("2","电子邮件"),
        ITEM_4("4","手机短信"),
        ITEM_8("8","MSN消息"),
        ITEM_16("16","检务通消息"),
        ITEM_32("32","微信");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList29(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[工作日类型]
     */
    @Getter
    public enum CodeList45 {
        ITEM_1("1","工作日"),
        ITEM_2("2","非工作日"),
        ITEM_3("3","自定义工作日");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList45(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[扩展表格单元格水平对齐方式]
     */
    @Getter
    public enum CodeList74 {
        LEFT("LEFT","左对齐"),
        CENTER("CENTER","居中"),
        RIGHT("RIGHT","右对齐");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList74(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[项目快速分组（我的地盘）]
     */
    @Getter
    public enum ProjectQuickpaketMy {
        ALL("All","我的项目");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        ProjectQuickpaketMy(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[团队类型]
     */
    @Getter
    public enum Team__type {
        PROJECT("project","项目团队"),
        TASK("task","任务团队"),
        PRODUCT("product","产品团队");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Team__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[ZT_IM_CONFERENCE__STATUS]
     */
    @Getter
    public enum Im_conference__status {
        CLOSED("closed","closed"),
        OPEN("open","open");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Im_conference__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[是否指派]
     */
    @Getter
    public enum IsAssigned {
        ASSIGN("assign","已指派"),
        NOASSIGN("noassign","未指派");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        IsAssigned(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[文件编码]
     */
    @Getter
    public enum CodeList39 {
        ANSI("ANSI","ANSI"),
        UTF_SUB_8("UTF-8","UTF-8");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList39(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }




    /**
     * 代码表[输入辅助_控件参数]
     */
    @Getter
    public enum CodeList101 {
        CARETTEMPLGROUP_SRFDA_CONTROLPARAM("CARETTEMPLGROUP_SRFDA_CONTROLPARAM","控件基本参数"),
        CARETTEMPLGROUP_SRFDA_CONTROLPARAM_CARET("CARETTEMPLGROUP_SRFDA_CONTROLPARAM_CARET","辅助输入控件参数"),
        CARETTEMPLGROUP_SRFDA_CONTROLPARAM_PICKUP("CARETTEMPLGROUP_SRFDA_CONTROLPARAM_PICKUP","选择控件参数"),
        CARETTEMPLGROUP_SRFDA_CONTROLPARAM_TEXTBOX("CARETTEMPLGROUP_SRFDA_CONTROLPARAM_TEXTBOX","文本控件参数"),
        CARETTEMPLGROUP_SRFDA_CONTROLPARAM_PICKUPLISTBOX("CARETTEMPLGROUP_SRFDA_CONTROLPARAM_PICKUPLISTBOX","选择列表控件参数");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList101(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[需求优先级]
     */
    @Getter
    public enum Story__pri {
        ITEM_1(1,"1"),
        ITEM_2(2,"2"),
        ITEM_3(3,"3"),
        ITEM_4(4,"4");

        private int value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Story__pri(int value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[实体属性表单默认值类型]
     */
    @Getter
    public enum CodeList3 {
        SESSION("SESSION","用户全局对象"),
        APPLICATION("APPLICATION","系统全局对象"),
        UNIQUEID("UNIQUEID","唯一编码"),
        CONTEXT("CONTEXT","网页请求"),
        PARAM("PARAM","数据对象属性"),
        OPERATOR("OPERATOR","当前操作用户(编号)"),
        OPERATORNAME("OPERATORNAME","当前操作用户(名称)"),
        CURTIME("CURTIME","当前时间");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList3(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[来源区块]
     */
    @Getter
    public enum Block__source_block {
        STATISTIC("statistic","统计"),
        OVERVIEW("overview","总览"),
        LIST("list","列表"),
        TASK("task","任务"),
        BUG("bug","bug"),
        CASE("case","用例"),
        TESTTASK("testtask","测试版本");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Block__source_block(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[实体属性插入模式]
     */
    @Getter
    public enum CodeList1 {
        VERSION("VERSION","版本模式");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList1(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[Bug解决方案]
     */
    @Getter
    public enum Bug__resolution {
        BYDESIGN("bydesign","设计如此"),
        DUPLICATE("duplicate","重复Bug"),
        EXTERNAL("external","外部原因"),
        FIXED("fixed","已解决"),
        NOTREPRO("notrepro","无法重现"),
        POSTPONED("postponed","延期处理"),
        WILLNOTFIX("willnotfix","不予解决"),
        TOSTORY("tostory","转为需求");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Bug__resolution(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[日志级别]
     */
    @Getter
    public enum CodeList32 {
        ITEM_50000("50000","致命(FATAL)"),
        ITEM_40000("40000","错误(ERROR)"),
        ITEM_30000("30000","警告(WARN)"),
        ITEM_20000("20000","信息(INFO)"),
        ITEM_10000("10000","调试(DEBUG)"),
        ITEM_5000("5000","调试(TRACE)");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList32(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[时间维度类型]
     */
    @Getter
    public enum CodeList87 {
        YM("YM","年、月"),
        YMW("YMW","年、月、周"),
        YMWD("YMWD","年、月、周、天"),
        YMWDH("YMWDH","年、月、周、天、小时"),
        YMD("YMD","年、月、天"),
        YMDH("YMDH","年、月、天、小时"),
        YW("YW","年、周"),
        YWD("YWD","年、周、天"),
        YWDH("YWDH","年、周、天、小时");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList87(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }




    /**
     * 代码表[更新平台]
     */
    @Getter
    public enum SYS_UPDATE_BRANCH {
        PC("PC","PC"),
        MOB("MOB","MOB");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        SYS_UPDATE_BRANCH(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[附件对象类型]
     */
    @Getter
    public enum File__object_type {
        PRODUCT("product","产品"),
        STORY("story","需求"),
        PRODUCTPLAN("productplan","计划"),
        RELEASE("release","发布"),
        PROJECT("project","项目"),
        TASK("task","任务"),
        BUILD("build","版本"),
        BUG("bug","Bug"),
        CASE("case","用例"),
        CASERESULT("caseresult","用例结果"),
        STEPRESULT("stepresult","用例步骤"),
        TESTTASK("testtask","测试单"),
        USER("user","用户"),
        DOC("doc","文档"),
        DOCLIB("doclib","文档库"),
        TODO("todo","待办"),
        BRANCH("branch","分支"),
        MODULE("module","模块"),
        TESTSUITE("testsuite","套件"),
        CASELIB("caselib","用例库"),
        TESTREPORT("testreport","报告"),
        ENTRY("entry","应用"),
        WEBHOOK("webhook","Webhook"),
        DAILY("daily","日报"),
        WEEKLY("weekly","周报"),
        MONTHLY("monthly","月报"),
        REPORTLY("reportly","汇报");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        File__object_type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[ZT_CASE__FREQUENCY]
     */
    @Getter
    public enum Case__frequency {
        ITEM_1("1","1"),
        ITEM_2("2","2"),
        ITEM_3("3","3");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Case__frequency(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[物理信息更新模式]
     */
    @Getter
    public enum CodeList57 {
        UPDATEWHENMODIFY("UPDATEWHENMODIFY","变更时更新");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList57(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[模板类型]
     */
    @Getter
    public enum Usertpl__type {
        STORY("story","story"),
        TASK("task","task"),
        BUG("bug","bug");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Usertpl__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[周期类型]
     */
    @Getter
    public enum CycleType {
        DAY("day","天"),
        WEEK("week","周"),
        MONTH("month","月度");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CycleType(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[需求快速分组（移动端）]
     */
    @Getter
    public enum MobStoryQuickGroup {
        ALL("ALL","所有"),
        UNCLOSED("UNCLOSED","未关闭"),
        TOME("TOME","指给我"),
        ICREATE("ICREATE","我创建"),
        MORE("MORE","更多"),
        IREVIEW("IREVIEW","我评审"),
        DRAFT("DRAFT","草稿"),
        ICLOSE("ICLOSE","我关闭"),
        ACTIVED("ACTIVED","激活"),
        CHANGED("CHANGED","已变更"),
        TOBECLOSED("TOBECLOSED","待关闭"),
        CLOSED("CLOSED","已关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        MobStoryQuickGroup(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[需求评审结果]
     */
    @Getter
    public enum Story__review_result {
        PASS("pass","确认通过"),
        REVERT("revert","撤销变更"),
        CLARIFY("clarify","有待明确"),
        REJECT("reject","拒绝");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Story__review_result(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[统一资源类型]
     */
    @Getter
    public enum CodeList16 {
        PAGE("PAGE","内置页面"),
        REPORT("REPORT","报表"),
        CUSTOM("CUSTOM","自定义");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList16(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[缩略界面类型]
     */
    @Getter
    public enum CodeList7 {
        FORM("FORM","表单"),
        PAGE("PAGE","内置页面");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList7(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[需求类型]
     */
    @Getter
    public enum Story__type {
        REQUIREMENT("requirement","用户需求"),
        STORY("story","软件需求");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Story__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[日期_待定]
     */
    @Getter
    public enum Date_disable {
        ON("on","待定");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Date_disable(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[功能测试快速分组（我的地盘）]
     */
    @Getter
    public enum CaseQuickpacketMy {
        TOME("TOME","给我的用例"),
        ICREATE("ICREATE","我建的用例");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CaseQuickpacketMy(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[项目快速分组]
     */
    @Getter
    public enum Project_quickpacket {
        ALL("All","所有"),
        WAIT("wait","未开始"),
        MORE("MORE","更多"),
        DOING("doing","进行中"),
        SUSPENDED("suspended","已挂起"),
        CLOSED("closed","已关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Project_quickpacket(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[Bug浏览器]
     */
    @Getter
    public enum Bug__browser {
        ALL("all","全部"),
        IE("ie","IE系列"),
        IE11("ie11","IE11"),
        IE10("ie10","IE10"),
        IE9("ie9","IE9"),
        IE8("ie8","IE8"),
        IE7("ie7","IE7"),
        IE6("ie6","IE6"),
        CHROME("chrome","chrome"),
        FIREFOX("firefox","firefox系列"),
        FIREFOX4("firefox4","firefox4"),
        FIREFOX3("firefox3","firefox3"),
        FIREFOX2("firefox2","firefox2"),
        OPERA("opera","opera系列"),
        OPERA11("opera11","opera11"),
        OPREA10("oprea10","oprea10"),
        OPERA9("opera9","opera9"),
        SAFARI("safari","safari"),
        MAXTHON("maxthon","傲游"),
        UC("uc","UC"),
        OTHERS("others","其他");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Bug__browser(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[测试用例阶段]
     */
    @Getter
    public enum Testcase__stage {
        UNITTEST("unittest","单元测试阶段"),
        FEATURE("feature","功能测试阶段"),
        INTERGRATE("intergrate","集成测试阶段"),
        SYSTEM("system","系统测试阶段"),
        SMOKE("smoke","冒烟测试阶段"),
        BVT("bvt","版本验证阶段");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Testcase__stage(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[插件_消息类型]
     */
    @Getter
    public enum Message__type {
        TODO("todo","待办"),
        TOREAD("toread","待阅");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Message__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[待办类型（全部）]
     */
    @Getter
    public enum TypeAll {
        CUSTOM("custom","自定义"),
        BUG("bug","Bug"),
        TASK("task","项目任务"),
        STORY("story","项目需求"),
        CYCLE("cycle","周期");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        TypeAll(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[文档库类型]
     */
    @Getter
    public enum Doclib__type {
        PRODUCT("product","产品文档库"),
        PROJECT("project","项目文档库"),
        CUSTOM("custom","自定义文档库");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Doclib__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[周期时间类型]
     */
    @Getter
    public enum CodeList40 {
        MONTH("MONTH","月度"),
        SEASON("SEASON","季度"),
        WEEK("WEEK","周"),
        DAY("DAY","天");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList40(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }




    /**
     * 代码表[IBIZ模板类型]
     */
    @Getter
    public enum Ibz__systpl__type {
        TPLTYPE__FREEMARKER("tpltype__freemarker","Freemarker");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Ibz__systpl__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试套件&用例库类型（新建套件）]
     */
    @Getter
    public enum TestsuiteTypeNew {
        PRIVATE("private","私有"),
        PUBLIC("public","公开");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        TestsuiteTypeNew(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[待办优先级]
     */
    @Getter
    public enum Pri {
        ITEM_1(1,"一般"),
        ITEM_2(2,"最高"),
        ITEM_3(3,"较高"),
        ITEM_4(4,"最低");

        private int value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Pri(int value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[计划快速分组]
     */
    @Getter
    public enum Zt__productplan {
        ALL("All","所有"),
        UNEXPIRED("UNEXPIRED","未过期"),
        ISEXPIRED("ISEXPIRED","已过期");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Zt__productplan(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[Bug快速分组（我的地盘）]
     */
    @Getter
    public enum BugQuickpacketMy {
        TOME("TOME","指派给我"),
        ICREATE("ICREATE","由我创建"),
        BYME("BYME","由我解决"),
        WAITCLOSED("WAITCLOSED","由我关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        BugQuickpacketMy(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[文档类型]
     */
    @Getter
    public enum Doc__type {
        TEXT("text","文档"),
        URL("url","链接");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Doc__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[发布状态]
     */
    @Getter
    public enum Release__status {
        NORMAL("normal","正常"),
        TERMINATE("terminate","停止维护");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Release__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[实体数据操作]
     */
    @Getter
    public enum CodeList10 {
        INSERT("INSERT","插入"),
        UPDATE("UPDATE","更新"),
        DELETE("DELETE","删除"),
        SELECT("SELECT","简单查询"),
        CUSTOMCALL("CUSTOMCALL","自定义"),
        CUSTOMPROCCALL("CUSTOMPROCCALL","自定义存储过程");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList10(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[补丁归属]
     */
    @Getter
    public enum CodeList36 {
        ITEM_1("1","框架基本"),
        ITEM_2("2","框架高级"),
        ITEM_4("4","工作流"),
        ITEM_8("8","EAI"),
        ITEM_16("16","UAC"),
        ITEM_32("32","全文检索"),
        ITEM_64("64","数据分析"),
        ITEM_128("128","基础网盘"),
        ITEM_256("256","基础组织");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList36(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[动态面板分区缩放样式]
     */
    @Getter
    public enum CodeList93 {
        EXPAND("EXPAND","展开"),
        COLLAPSE("COLLAPSE","收缩");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList93(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[ZT_WEBHOOK__SENDTYPE]
     */
    @Getter
    public enum Webhook__sendType {
        SYNC("sync","sync"),
        ASYNC("async","async");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Webhook__sendType(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[时间分组类型]
     */
    @Getter
    public enum CodeList92 {
        Q("Q","季度"),
        M("M","月份"),
        D("D","月天"),
        H("H","小时");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList92(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[实体数据处理_数据对象操作]
     */
    @Getter
    public enum CodeList107 {
        CREATENEW("CREATENEW","新建数据对象"),
        CREATEFROM("CREATEFROM","拷贝新建数据对象"),
        COPY("COPY","拷贝数据对象"),
        COPYRESET("COPYRESET","拷贝数据对象(重置)");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList107(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }




    /**
     * 代码表[实体属性访问控制]
     */
    @Getter
    public enum CodeList88 {
        NONE("NONE","无"),
        READ("READ","读取"),
        UPDATE("UPDATE","更新");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList88(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试用例颜色]
     */
    @Getter
    public enum Testcase__color {
        ITEM_1("#3da7f5","#3da7f5"),
        ITEM_2("#75c941","#75c941"),
        ITEM_3("#2dbdb2","#2dbdb2"),
        ITEM_4("#797ec9","#797ec9"),
        ITEM_5("#ffaf38","#ffaf38"),
        ITEM_6("#ff4e3e","#ff4e3e");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Testcase__color(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[起止时间选择(下拉列表)]
     */
    @Getter
    public enum BeginendDropList {
        ITEM_600(600,"06:00"),
        ITEM_610(610,"06:10"),
        ITEM_620(620,"06:20"),
        ITEM_630(630,"06:30"),
        ITEM_640(640,"06:40"),
        ITEM_650(650,"06:50"),
        ITEM_700(700,"07:00"),
        ITEM_710(710,"07:10"),
        ITEM_720(720,"07:20"),
        ITEM_730(730,"07:30"),
        ITEM_740(740,"07:40"),
        ITEM_750(750,"07:50"),
        ITEM_800(800,"08:00"),
        ITEM_810(810,"08:10"),
        ITEM_820(820,"08:20"),
        ITEM_830(830,"08:30"),
        ITEM_840(840,"08:40"),
        ITEM_850(850,"08:50"),
        ITEM_900(900,"09:00"),
        ITEM_910(910,"09:10"),
        ITEM_920(920,"09:20"),
        ITEM_930(930,"09:30"),
        ITEM_940(940,"09:40"),
        ITEM_950(950,"09:50"),
        ITEM_1000(1000,"10:00"),
        ITEM_1010(1010,"10:10"),
        ITEM_1020(1020,"10:20"),
        ITEM_1030(1030,"10:30"),
        ITEM_1040(1040,"10:40"),
        ITEM_1050(1050,"10:50"),
        ITEM_1100(1100,"11:00"),
        ITEM_1110(1110,"11:10"),
        ITEM_1120(1120,"11:20"),
        ITEM_1130(1130,"11:30"),
        ITEM_1140(1140,"11:40"),
        ITEM_1150(1150,"11:50"),
        ITEM_1200(1200,"12:00"),
        ITEM_1210(1210,"12:10"),
        ITEM_1220(1220,"12:20"),
        ITEM_1230(1230,"12:30"),
        ITEM_1240(1240,"12:40"),
        ITEM_1250(1250,"12:50"),
        ITEM_1300(1300,"13:00"),
        ITEM_1310(1310,"13:10"),
        ITEM_1320(1320,"13:20"),
        ITEM_1330(1330,"13:30"),
        ITEM_1340(1340,"13:40"),
        ITEM_1350(1350,"13:50"),
        ITEM_1400(1400,"14:00"),
        ITEM_1410(1410,"14:10"),
        ITEM_1420(1420,"14:20"),
        ITEM_1430(1430,"14:30"),
        ITEM_1440(1440,"14:40"),
        ITEM_1450(1450,"14:50"),
        ITEM_1500(1500,"15:00"),
        ITEM_1510(1510,"15:10"),
        ITEM_1520(1520,"15:20"),
        ITEM_1530(1530,"15:30"),
        ITEM_1540(1540,"15:40"),
        ITEM_1550(1550,"15:50"),
        ITEM_1600(1600,"16:00"),
        ITEM_1610(1610,"16:10"),
        ITEM_1620(1620,"16:20"),
        ITEM_1630(1630,"16:30"),
        ITEM_1640(1640,"16:40"),
        ITEM_1650(1650,"16:50"),
        ITEM_1700(1700,"17:00"),
        ITEM_1710(1710,"17:10"),
        ITEM_1720(1720,"17:20"),
        ITEM_1730(1730,"17:30"),
        ITEM_1740(1740,"17:40"),
        ITEM_1750(1750,"17:50"),
        ITEM_1800(1800,"18:00"),
        ITEM_1810(1810,"18:10"),
        ITEM_1820(1820,"18:20"),
        ITEM_1830(1830,"18:30"),
        ITEM_1840(1840,"18:40"),
        ITEM_1850(1850,"18:50"),
        ITEM_1900(1900,"19:00"),
        ITEM_1910(1910,"19:10"),
        ITEM_1920(1920,"19:20"),
        ITEM_1930(1930,"19:30"),
        ITEM_1940(1940,"19:40"),
        ITEM_1950(1950,"19:50"),
        ITEM_2000(2000,"20:00"),
        ITEM_2010(2010,"20:10"),
        ITEM_2020(2020,"20:20"),
        ITEM_2030(2030,"20:30"),
        ITEM_2040(2040,"20:40"),
        ITEM_2050(2050,"20:50"),
        ITEM_2100(2100,"21:00"),
        ITEM_2110(2110,"21:10"),
        ITEM_2120(2120,"21:20"),
        ITEM_2130(2130,"21:30"),
        ITEM_2140(2140,"21:40"),
        ITEM_2150(2150,"21:50"),
        ITEM_2200(2200,"22:00"),
        ITEM_2210(2210,"22:10"),
        ITEM_2220(2220,"22:20"),
        ITEM_2230(2230,"22:30"),
        ITEM_2240(2240,"22:40"),
        ITEM_2250(2250,"22:50"),
        ITEM_2300(2300,"23:00"),
        ITEM_2310(2310,"23:10"),
        ITEM_2320(2320,"23:20"),
        ITEM_2330(2330,"23:30"),
        ITEM_2340(2340,"23:40"),
        ITEM_2350(2350,"23:50");

        private int value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        BeginendDropList(int value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[项目阶段]
     */
    @Getter
    public enum Project__statge {
        ITEM_1("1","1"),
        ITEM_2("2","2"),
        ITEM_3("3","3"),
        ITEM_4("4","4"),
        ITEM_5("5","5");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Project__statge(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[实体存储类型]
     */
    @Getter
    public enum CodeList80 {
        STATIC("STATIC","静态存储"),
        DYNAMIC("DYNAMIC","动态存储"),
        NONE("NONE","无存储");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList80(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[Bug优先级]
     */
    @Getter
    public enum Bug__pri {
        ITEM_1(1,"严重"),
        ITEM_2(2,"主要"),
        ITEM_3(3,"次要"),
        ITEM_4(4,"不重要");

        private int value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Bug__pri(int value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[图表控件_表格位置]
     */
    @Getter
    public enum CodeList52 {
        NONE("NONE","无表格"),
        TOPLEFT("TOPLEFT","上左"),
        TOP("TOP","上中"),
        TOPRIGHT("TOPRIGHT","上右"),
        BOTTOMLEFT("BOTTOMLEFT","下左"),
        BOTTOM("BOTTOM","下中"),
        BOTTOMRIGHT("BOTTOMRIGHT","下右"),
        LEFTTOP("LEFTTOP","左上"),
        LEFT("LEFT","左中"),
        LEFTBOTTOM("LEFTBOTTOM","左下"),
        RIGHTTOP("RIGHTTOP","右上"),
        RIGHT("RIGHT","右中"),
        RIGHTBOTTOM("RIGHTBOTTOM","右下");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList52(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[日历、邮件重要程度]
     */
    @Getter
    public enum CodeList8 {
        HIGH("HIGH","高"),
        NORMAL("NORMAL","普通"),
        LOW("LOW","低");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList8(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[输入辅助_实体处理逻辑代码]
     */
    @Getter
    public enum CodeList103 {
        CARETTEMPLGROUP_SRFDA_DEDCCONTEXT("CARETTEMPLGROUP_SRFDA_DEDCCONTEXT","实体处理逻辑引擎"),
        CARETTEMPLGROUP_SRFDA_DEACTION("CARETTEMPLGROUP_SRFDA_DEACTION","实体属性操作");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList103(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[需求模块类型]
     */
    @Getter
    public enum Ibizpro_storymodule__type {
        PMSSTORYMODULE("pmsStoryModule","PMS需求模块"),
        IBIZSYSMODULE("iBizSysModule","iBiz系统模块"),
        IBIZREQMODULE("iBizReqModule","iBiz需求模块");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Ibizpro_storymodule__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[系统错误代码]
     */
    @Getter
    public enum CodeList35 {
        ITEM_0("0","正确(0)"),
        ITEM_1("1","内部发生错误(INTERNALERROR)"),
        ITEM_2("2","访问被拒绝(ACCESSDENY)"),
        ITEM_3("3","无效的数据(INVALIDDATA)"),
        ITEM_4("4","无效的数据键(INVALIDDATAKEYS)"),
        ITEM_5("5","输入的信息有误(INPUTERROR)"),
        ITEM_6("6","重复的数据键值(DUPLICATEKEY)"),
        ITEM_7("7","重复的数据(DUPLICATEDATA)"),
        ITEM_8("8","删除拒绝(DELETEREJECT)"),
        ITEM_9("9","逻辑处理错误(LOGICERROR)");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList35(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[周期选择_启用]
     */
    @Getter
    public enum Cycle_enable {
        ITEM_1("1","周期");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Cycle_enable(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[语言资源类型]
     */
    @Getter
    public enum CodeList55 {
        DEF_LNAME("DEF.LNAME","实体属性逻辑名称"),
        CL_ITEM_LNAME("CL.ITEM.LNAME","代码表项"),
        TBB_TEXT("TBB.TEXT","工具栏按钮文本"),
        TBB_TOOLTIP("TBB.TOOLTIP","工具栏按钮提示"),
        MENUITEM_CAPTION("MENUITEM.CAPTION","菜单项文本"),
        PAGE_HEADER("PAGE.HEADER","界面头部标题"),
        PAGE_COMMON("PAGE.COMMON","界面常规"),
        CONTROL("CONTROL","控件文本"),
        ERROR_STD("ERROR.STD","标准错误"),
        CTRL("CTRL","处理逻辑"),
        COMMON("COMMON","通用"),
        OTHER("OTHER","其它");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList55(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试用例状态]
     */
    @Getter
    public enum Testcase__status {
        WAIT("wait","待评审"),
        NORMAL("normal","正常"),
        BLOCKED("blocked","被阻塞"),
        INVESTIGATE("investigate","研究中");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Testcase__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[数据库类型]
     */
    @Getter
    public enum CodeList33 {
        DB2("DB2","DB2"),
        ORACLE("ORACLE","ORACLE"),
        MSSQL("MSSQL","MSSQLSERVER"),
        MYSQL("MYSQL","MySQL"),
        SYBASE("SYBASE","SYBASE"),
        INFORMIX("INFORMIX","INFORMIX");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList33(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[时区]
     */
    @Getter
    public enum CodeList59 {
        ETC_GMT_ADD_12("Etc/GMT+12","(UTC-12:00)GMT-12:00"),
        ETC_GMT_ADD_11("Etc/GMT+11","(UTC-11:00)GMT-11:00"),
        MIT("MIT","(UTC-11:00)West Samoa Time"),
        PACIFIC_MIDWAY("Pacific/Midway","(UTC-11:00)Samoa Standard Time"),
        PACIFIC_NIUE("Pacific/Niue","(UTC-11:00)Niue Time"),
        AMERICA_ADAK("America/Adak","(UTC-10:00)Hawaii-Aleutian Standard Time"),
        ETC_GMT_ADD_10("Etc/GMT+10","(UTC-10:00)GMT-10:00"),
        HST("HST","(UTC-10:00)Hawaii Standard Time"),
        PACIFIC_FAKAOFO("Pacific/Fakaofo","(UTC-10:00)Tokelau Time"),
        PACIFIC_RAROTONGA("Pacific/Rarotonga","(UTC-10:00)Cook Is. Time"),
        PACIFIC_TAHITI("Pacific/Tahiti","(UTC-10:00)Tahiti Time"),
        PACIFIC_MARQUESAS("Pacific/Marquesas","(UTC-9:00)Marquesas Time"),
        AST("AST","(UTC-9:00)Alaska Standard Time"),
        ETC_GMT_ADD_9("Etc/GMT+9","(UTC-9:00)GMT-09:00"),
        PACIFIC_GAMBIER("Pacific/Gambier","(UTC-9:00)Gambier Time"),
        AMERICA_DAWSON("America/Dawson","(UTC-8:00)Pacific Standard Time"),
        ETC_GMT_ADD_8("Etc/GMT+8","(UTC-8:00)GMT-08:00"),
        PACIFIC_PITCAIRN("Pacific/Pitcairn","(UTC-8:00)Pitcairn Standard Time"),
        AMERICA_BOISE("America/Boise","(UTC-7:00)Mountain Standard Time"),
        ETC_GMT_ADD_7("Etc/GMT+7","(UTC-7:00)GMT-07:00"),
        AMERICA_BELIZE("America/Belize","(UTC-6:00)Central Standard Time"),
        CHILE_EASTERISLAND("Chile/EasterIsland","(UTC-6:00)Easter Is. Time"),
        ETC_GMT_ADD_6("Etc/GMT+6","(UTC-6:00)GMT-06:00"),
        PACIFIC_GALAPAGOS("Pacific/Galapagos","(UTC-6:00)Galapagos Time"),
        AMERICA_ATIKOKAN("America/Atikokan","(UTC-5:00)Eastern Standard Time"),
        AMERICA_BOGOTA("America/Bogota","(UTC-5:00)Colombia Time"),
        AMERICA_EIRUNEPE("America/Eirunepe","(UTC-5:00)Acre Time"),
        AMERICA_GUAYAQUIL("America/Guayaquil","(UTC-5:00)Ecuador Time"),
        AMERICA_HAVANA("America/Havana","(UTC-5:00)Cuba Standard Time"),
        AMERICA_LIMA("America/Lima","(UTC-5:00)Peru Time"),
        ETC_GMT_ADD_5("Etc/GMT+5","(UTC-5:00)GMT-05:00"),
        AMERICA_CARACAS("America/Caracas","(UTC-4:00)Venezuela Time"),
        AMERICA_ANGUILLA("America/Anguilla","(UTC-4:00)Atlantic Standard Time"),
        AMERICA_ASUNCION("America/Asuncion","(UTC-4:00)Paraguay Time"),
        AMERICA_BOA_VISTA("America/Boa_Vista","(UTC-4:00)Amazon Time"),
        AMERICA_GUYANA("America/Guyana","(UTC-4:00)Guyana Time"),
        AMERICA_LA_PAZ("America/La_Paz","(UTC-4:00)Bolivia Time"),
        AMERICA_SANTIAGO("America/Santiago","(UTC-4:00)Chile Time"),
        ATLANTIC_STANLEY("Atlantic/Stanley","(UTC-4:00)Falkland Is. Time"),
        ETC_GMT_ADD_4("Etc/GMT+4","(UTC-4:00)GMT-04:00"),
        AMERICA_ST_JOHNS("America/St_Johns","(UTC-3:00)Newfoundland Standard Time"),
        AGT("AGT","(UTC-3:00)Argentine Time"),
        AMERICA_ARAGUAINA("America/Araguaina","(UTC-3:00)Brasilia Time"),
        AMERICA_CAYENNE("America/Cayenne","(UTC-3:00)French Guiana Time"),
        AMERICA_GODTHAB("America/Godthab","(UTC-3:00)Western Greenland Time"),
        AMERICA_MIQUELON("America/Miquelon","(UTC-3:00)Pierre & Miquelon Standard Time"),
        AMERICA_MONTEVIDEO("America/Montevideo","(UTC-3:00)Uruguay Time"),
        AMERICA_PARAMARIBO("America/Paramaribo","(UTC-3:00)Suriname Time"),
        ANTARCTICA_ROTHERA("Antarctica/Rothera","(UTC-3:00)Rothera Time"),
        ETC_GMT_ADD_3("Etc/GMT+3","(UTC-3:00)GMT-03:00"),
        AMERICA_NORONHA("America/Noronha","(UTC-2:00)Fernando de Noronha Time"),
        ATLANTIC_SOUTH_GEORGIA("Atlantic/South_Georgia","(UTC-2:00)South Georgia Standard Time"),
        ETC_GMT_ADD_2("Etc/GMT+2","(UTC-2:00)GMT-02:00"),
        AMERICA_SCORESBYSUND("America/Scoresbysund","(UTC-1:00)Eastern Greenland Time"),
        ATLANTIC_AZORES("Atlantic/Azores","(UTC-1:00)Azores Time"),
        ATLANTIC_CAPE_VERDE("Atlantic/Cape_Verde","(UTC-1:00)Cape Verde Time"),
        ETC_GMT_ADD_1("Etc/GMT+1","(UTC-1:00)GMT-01:00"),
        AFRICA_ABIDJAN("Africa/Abidjan","(UTC0:00)Greenwich Mean Time"),
        AFRICA_ACCRA("Africa/Accra","(UTC0:00)Ghana Mean Time"),
        AFRICA_CASABLANCA("Africa/Casablanca","(UTC0:00)Western European Time"),
        ETC_GMT("Etc/GMT","(UTC0:00)GMT+00:00"),
        ETC_UCT("Etc/UCT","(UTC0:00)Coordinated Universal Time"),
        AFRICA_ALGIERS("Africa/Algiers","(UTC+1:00)Central European Time"),
        AFRICA_BANGUI("Africa/Bangui","(UTC+1:00)Western African Time"),
        ETC_GMT_SUB_1("Etc/GMT-1","(UTC+1:00)GMT+01:00"),
        MET("MET","(UTC+1:00)Middle Europe Time"),
        ART("ART","(UTC+2:00)Eastern European Time"),
        AFRICA_BLANTYRE("Africa/Blantyre","(UTC+2:00)Central African Time"),
        AFRICA_JOHANNESBURG("Africa/Johannesburg","(UTC+2:00)South Africa Standard Time"),
        ASIA_JERUSALEM("Asia/Jerusalem","(UTC+2:00)Israel Standard Time"),
        ETC_GMT_SUB_2("Etc/GMT-2","(UTC+2:00)GMT+02:00"),
        AFRICA_ADDIS_ABABA("Africa/Addis_Ababa","(UTC+3:00)Eastern African Time"),
        ANTARCTICA_SYOWA("Antarctica/Syowa","(UTC+3:00)Syowa Time"),
        ASIA_ADEN("Asia/Aden","(UTC+3:00)Arabia Standard Time"),
        ETC_GMT_SUB_3("Etc/GMT-3","(UTC+3:00)GMT+03:00"),
        EUROPE_MOSCOW("Europe/Moscow","(UTC+3:00)Moscow Standard Time"),
        EUROPE_VOLGOGRAD("Europe/Volgograd","(UTC+3:00)Volgograd Time"),
        ASIA_RIYADH87("Asia/Riyadh87","(UTC+3:00)GMT+03:07"),
        ASIA_TEHRAN("Asia/Tehran","(UTC+3:00)Iran Standard Time"),
        ASIA_BAKU("Asia/Baku","(UTC+4:00)Azerbaijan Time"),
        ASIA_DUBAI("Asia/Dubai","(UTC+4:00)Gulf Standard Time"),
        ASIA_TBILISI("Asia/Tbilisi","(UTC+4:00)Georgia Time"),
        ASIA_YEREVAN("Asia/Yerevan","(UTC+4:00)Armenia Time"),
        ETC_GMT_SUB_4("Etc/GMT-4","(UTC+4:00)GMT+04:00"),
        EUROPE_SAMARA("Europe/Samara","(UTC+4:00)Samara Time"),
        INDIAN_MAHE("Indian/Mahe","(UTC+4:00)Seychelles Time"),
        INDIAN_MAURITIUS("Indian/Mauritius","(UTC+4:00)Mauritius Time"),
        INDIAN_REUNION("Indian/Reunion","(UTC+4:00)Reunion Time"),
        ASIA_KABUL("Asia/Kabul","(UTC+4:00)Afghanistan Time"),
        ASIA_AQTAU("Asia/Aqtau","(UTC+5:00)Aqtau Time"),
        ASIA_AQTOBE("Asia/Aqtobe","(UTC+5:00)Aqtobe Time"),
        ASIA_ASHGABAT("Asia/Ashgabat","(UTC+5:00)Turkmenistan Time"),
        ASIA_DUSHANBE("Asia/Dushanbe","(UTC+5:00)Tajikistan Time"),
        ASIA_KARACHI("Asia/Karachi","(UTC+5:00)Pakistan Time"),
        ASIA_ORAL("Asia/Oral","(UTC+5:00)Oral Time"),
        ASIA_SAMARKAND("Asia/Samarkand","(UTC+5:00)Uzbekistan Time"),
        ASIA_YEKATERINBURG("Asia/Yekaterinburg","(UTC+5:00)Yekaterinburg Time"),
        ETC_GMT_SUB_5("Etc/GMT-5","(UTC+5:00)GMT+05:00"),
        INDIAN_KERGUELEN("Indian/Kerguelen","(UTC+5:00)French Southern & Antarctic Lands Time"),
        INDIAN_MALDIVES("Indian/Maldives","(UTC+5:00)Maldives Time"),
        ASIA_CALCUTTA("Asia/Calcutta","(UTC+5:00)India Standard Time"),
        ASIA_KATMANDU("Asia/Katmandu","(UTC+5:00)Nepal Time"),
        ANTARCTICA_MAWSON("Antarctica/Mawson","(UTC+6:00)Mawson Time"),
        ANTARCTICA_VOSTOK("Antarctica/Vostok","(UTC+6:00)Vostok Time"),
        ASIA_ALMATY("Asia/Almaty","(UTC+6:00)Alma-Ata Time"),
        ASIA_BISHKEK("Asia/Bishkek","(UTC+6:00)Kirgizstan Time"),
        ASIA_DACCA("Asia/Dacca","(UTC+6:00)Bangladesh Time"),
        ASIA_NOVOSIBIRSK("Asia/Novosibirsk","(UTC+6:00)Novosibirsk Time"),
        ASIA_OMSK("Asia/Omsk","(UTC+6:00)Omsk Time"),
        ASIA_QYZYLORDA("Asia/Qyzylorda","(UTC+6:00)Qyzylorda Time"),
        ASIA_THIMBU("Asia/Thimbu","(UTC+6:00)Bhutan Time"),
        ETC_GMT_SUB_6("Etc/GMT-6","(UTC+6:00)GMT+06:00"),
        INDIAN_CHAGOS("Indian/Chagos","(UTC+6:00)Indian Ocean Territory Time"),
        ASIA_RANGOON("Asia/Rangoon","(UTC+6:00)Myanmar Time"),
        INDIAN_COCOS("Indian/Cocos","(UTC+6:00)Cocos Islands Time"),
        ANTARCTICA_DAVIS("Antarctica/Davis","(UTC+7:00)Davis Time"),
        ASIA_BANGKOK("Asia/Bangkok","(UTC+7:00)Indochina Time"),
        ASIA_HOVD("Asia/Hovd","(UTC+7:00)Hovd Time"),
        ASIA_JAKARTA("Asia/Jakarta","(UTC+7:00)West Indonesia Time"),
        ASIA_KRASNOYARSK("Asia/Krasnoyarsk","(UTC+7:00)Krasnoyarsk Time"),
        ETC_GMT_SUB_7("Etc/GMT-7","(UTC+7:00)GMT+07:00"),
        INDIAN_CHRISTMAS("Indian/Christmas","(UTC+7:00)Christmas Island Time"),
        ANTARCTICA_CASEY("Antarctica/Casey","(UTC+8:00)Western Standard Time (Australia)"),
        ASIA_BRUNEI("Asia/Brunei","(UTC+8:00)Brunei Time"),
        ASIA_CHOIBALSAN("Asia/Choibalsan","(UTC+8:00)Choibalsan Time"),
        ASIA_SHANGHAI("Asia/Shanghai","(UTC+8:00)China Standard Time"),
        ASIA_HONG_KONG("Asia/Hong_Kong","(UTC+8:00)Hong Kong Time"),
        ASIA_IRKUTSK("Asia/Irkutsk","(UTC+8:00)Irkutsk Time"),
        ASIA_KUALA_LUMPUR("Asia/Kuala_Lumpur","(UTC+8:00)Malaysia Time"),
        ASIA_MAKASSAR("Asia/Makassar","(UTC+8:00)Central Indonesia Time"),
        ASIA_MANILA("Asia/Manila","(UTC+8:00)Philippines Time"),
        ASIA_SINGAPORE("Asia/Singapore","(UTC+8:00)Singapore Time"),
        ASIA_ULAANBAATAR("Asia/Ulaanbaatar","(UTC+8:00)Ulaanbaatar Time"),
        ETC_GMT_SUB_8("Etc/GMT-8","(UTC+8:00)GMT+08:00"),
        AUSTRALIA_EUCLA("Australia/Eucla","(UTC+8:00)Central Western Standard Time (Australia)"),
        ASIA_DILI("Asia/Dili","(UTC+9:00)Timor-Leste Time"),
        ASIA_JAYAPURA("Asia/Jayapura","(UTC+9:00)East Indonesia Time"),
        ASIA_PYONGYANG("Asia/Pyongyang","(UTC+9:00)Korea Standard Time"),
        ASIA_TOKYO("Asia/Tokyo","(UTC+9:00)Japan Standard Time"),
        ASIA_YAKUTSK("Asia/Yakutsk","(UTC+9:00)Yakutsk Time"),
        ETC_GMT_SUB_9("Etc/GMT-9","(UTC+9:00)GMT+09:00"),
        PACIFIC_PALAU("Pacific/Palau","(UTC+9:00)Palau Time"),
        ACT("ACT","(UTC+9:00)Central Standard Time (Northern Territory)"),
        AUSTRALIA_ADELAIDE("Australia/Adelaide","(UTC+9:00)Central Standard Time (South Australia)"),
        AUSTRALIA_BROKEN_HILL("Australia/Broken_Hill","(UTC+9:00)Central Standard Time (South Australia/New South Wales)"),
        AET("AET","(UTC+10:00)Eastern Standard Time (New South Wales)"),
        ANTARCTICA_DUMONTDURVILLE("Antarctica/DumontDUrville","(UTC+10:00)Dumont-d|Urville Time"),
        ASIA_SAKHALIN("Asia/Sakhalin","(UTC+10:00)Sakhalin Time"),
        ASIA_VLADIVOSTOK("Asia/Vladivostok","(UTC+10:00)Vladivostok Time"),
        AUSTRALIA_BRISBANE("Australia/Brisbane","(UTC+10:00)Eastern Standard Time (Queensland)"),
        AUSTRALIA_HOBART("Australia/Hobart","(UTC+10:00)Eastern Standard Time (Tasmania)"),
        AUSTRALIA_MELBOURNE("Australia/Melbourne","(UTC+10:00)Eastern Standard Time (Victoria)"),
        ETC_GMT_SUB_10("Etc/GMT-10","(UTC+10:00)GMT+10:00"),
        PACIFIC_GUAM("Pacific/Guam","(UTC+10:00)Chamorro Standard Time"),
        PACIFIC_PORT_MORESBY("Pacific/Port_Moresby","(UTC+10:00)Papua New Guinea Time"),
        PACIFIC_TRUK("Pacific/Truk","(UTC+10:00)Truk Time"),
        AUSTRALIA_LHI("Australia/LHI","(UTC+10:00)Lord Howe Standard Time"),
        ASIA_MAGADAN("Asia/Magadan","(UTC+11:00)Magadan Time"),
        ETC_GMT_SUB_11("Etc/GMT-11","(UTC+11:00)GMT+11:00"),
        PACIFIC_EFATE("Pacific/Efate","(UTC+11:00)Vanuatu Time"),
        PACIFIC_GUADALCANAL("Pacific/Guadalcanal","(UTC+11:00)Solomon Is. Time"),
        PACIFIC_KOSRAE("Pacific/Kosrae","(UTC+11:00)Kosrae Time"),
        PACIFIC_NOUMEA("Pacific/Noumea","(UTC+11:00)New Caledonia Time"),
        PACIFIC_PONAPE("Pacific/Ponape","(UTC+11:00)Ponape Time"),
        PACIFIC_NORFOLK("Pacific/Norfolk","(UTC+11:00)Norfolk Time"),
        ANTARCTICA_MCMURDO("Antarctica/McMurdo","(UTC+12:00)New Zealand Standard Time"),
        ASIA_ANADYR("Asia/Anadyr","(UTC+12:00)Anadyr Time"),
        ASIA_KAMCHATKA("Asia/Kamchatka","(UTC+12:00)Petropavlovsk-Kamchatski Time"),
        ETC_GMT_SUB_12("Etc/GMT-12","(UTC+12:00)GMT+12:00"),
        KWAJALEIN("Kwajalein","(UTC+12:00)Marshall Islands Time"),
        PACIFIC_FIJI("Pacific/Fiji","(UTC+12:00)Fiji Time"),
        PACIFIC_FUNAFUTI("Pacific/Funafuti","(UTC+12:00)Tuvalu Time"),
        PACIFIC_NAURU("Pacific/Nauru","(UTC+12:00)Nauru Time"),
        PACIFIC_TARAWA("Pacific/Tarawa","(UTC+12:00)Gilbert Is. Time"),
        PACIFIC_WAKE("Pacific/Wake","(UTC+12:00)Wake Time"),
        PACIFIC_WALLIS("Pacific/Wallis","(UTC+12:00)Wallis & Futuna Time"),
        NZ_SUB_CHAT("NZ-CHAT","(UTC+12:00)Chatham Standard Time"),
        ETC_GMT_SUB_13("Etc/GMT-13","(UTC+13:00)GMT+13:00"),
        PACIFIC_ENDERBURY("Pacific/Enderbury","(UTC+13:00)Phoenix Is. Time"),
        PACIFIC_TONGATAPU("Pacific/Tongatapu","(UTC+13:00)Tonga Time"),
        ETC_GMT_SUB_14("Etc/GMT-14","(UTC+14:00)GMT+14:00"),
        PACIFIC_KIRITIMATI("Pacific/Kiritimati","(UTC+14:00)Line Is. Time");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList59(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[Bug创建解决版本]
     */
    @Getter
    public enum BugCreateResolve {
        ITEM_1("1","创建");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        BugCreateResolve(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[文件库类型]
     */
    @Getter
    public enum DOCLIBTYPE {
        DOC("doc","文档"),
        FILE("file","附件");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        DOCLIBTYPE(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[输入辅助_图表参数]
     */
    @Getter
    public enum CodeList104 {
        CARETTEMPLGROUP_SRFREPORT_CHARTDATA("CARETTEMPLGROUP_SRFREPORT_CHARTDATA","图表数据定义"),
        CARETTEMPLGROUP_SRFREPORT_CHART("CARETTEMPLGROUP_SRFREPORT_CHART","图表表现定义");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList104(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[民族（公安部身份证）]
     */
    @Getter
    public enum CodeList99 {
        ITEM_01("01","汉"),
        ITEM_02("02","蒙古"),
        ITEM_03("03","回"),
        ITEM_04("04","藏"),
        ITEM_05("05","维吾尔"),
        ITEM_06("06","苗"),
        ITEM_07("07","彝"),
        ITEM_08("08","壮"),
        ITEM_09("09","布依"),
        ITEM_10("10","朝鲜"),
        ITEM_11("11","满"),
        ITEM_12("12","侗"),
        ITEM_13("13","瑶"),
        ITEM_14("14","白"),
        ITEM_15("15","土家"),
        ITEM_16("16","哈尼"),
        ITEM_17("17","哈萨克"),
        ITEM_18("18","傣"),
        ITEM_19("19","黎"),
        ITEM_20("20","傈僳"),
        ITEM_21("21","佤"),
        ITEM_22("22","畲"),
        ITEM_23("23","高山"),
        ITEM_24("24","拉祜"),
        ITEM_25("25","水"),
        ITEM_26("26","东乡"),
        ITEM_27("27","纳西"),
        ITEM_28("28","景颇"),
        ITEM_29("29","柯尔克孜"),
        ITEM_30("30","土"),
        ITEM_31("31","达斡尔"),
        ITEM_32("32","仫佬"),
        ITEM_33("33","羌"),
        ITEM_34("34","布朗"),
        ITEM_35("35","撒拉"),
        ITEM_36("36","毛南"),
        ITEM_37("37","仡佬"),
        ITEM_38("38","锡伯"),
        ITEM_39("39","阿昌"),
        ITEM_40("40","普米"),
        ITEM_41("41","塔吉克"),
        ITEM_42("42","怒"),
        ITEM_43("43","乌孜别克"),
        ITEM_44("44","俄罗斯"),
        ITEM_45("45","鄂温克"),
        ITEM_46("46","德昂"),
        ITEM_47("47","保安"),
        ITEM_48("48","裕固"),
        ITEM_49("49","京"),
        ITEM_50("50","塔塔尔"),
        ITEM_51("51","独龙"),
        ITEM_52("52","鄂伦春"),
        ITEM_53("53","赫哲"),
        ITEM_54("54","门巴"),
        ITEM_55("55","珞巴"),
        ITEM_56("56","基诺");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList99(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[系统更新类型]
     */
    @Getter
    public enum SYS_UPDATE_LOG_TYPE {
        ITEM_10("10","功能增强"),
        ITEM_20("20","优化");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        SYS_UPDATE_LOG_TYPE(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[主实体删除关系实体操作]
     */
    @Getter
    public enum CodeList21 {
        ITEM_1("1","同时删除"),
        ITEM_2("2","置空"),
        ITEM_3("3","限制删除");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList21(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[实体数据处理_数据操作]
     */
    @Getter
    public enum CodeList108 {
        INSERT("INSERT","新建"),
        UPDATE("UPDATE","更新"),
        SAVE("SAVE","保存（自动判断）"),
        DELETE("DELETE","删除"),
        CUSTOMCALL("CUSTOMCALL","自定义调用"),
        CUSTOMPROCCALL("CUSTOMPROCCALL","自定义存储过程调用"),
        CUSTOMRAWPROCCALL("CUSTOMRAWPROCCALL","自定义存储过程调用（全称）"),
        GET("GET","获取(GET)"),
        CHECKKEYSTATE("CHECKKEYSTATE","检查主键状态(CHECKKEYSTATE)");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList108(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[起止时间_暂时不设定时间]
     */
    @Getter
    public enum Beginend_disable {
        ON("on","暂时不设定时间");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Beginend_disable(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[需求阶段]
     */
    @Getter
    public enum Story__stage {
        WAIT("wait","未开始"),
        PLANNED("planned","已计划"),
        PROJECTED("projected","已立项"),
        DEVELOPING("developing","研发中"),
        DEVELOPED("developed","研发完毕"),
        TESTING("testing","测试中"),
        TESTED("tested","测试完毕"),
        VERIFIED("verified","已验收"),
        RELEASED("released","已发布"),
        CLOSED("closed","已关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Story__stage(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[前端展现技术]
     */
    @Getter
    public enum CodeList96 {
        HTML("HTML","HTML"),
        SL("SL","SilverLight"),
        WINRT("WinRT","WinRT"),
        ANDROID("Android","Android"),
        IOS("IOS","IOS");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList96(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[实体规则处理_数据类型]
     */
    @Getter
    public enum CodeList112 {
        VARCHAR("VARCHAR","文本"),
        INT("INT","整形"),
        FLOAT("FLOAT","浮点"),
        DATETIME("DATETIME","日期");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList112(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[实体关系明细类型]
     */
    @Getter
    public enum CodeList14 {
        PAGE("PAGE","内建页面"),
        PAGEPATH("PAGEPATH","页面路径"),
        DER1N("DER1N","1:N关系"),
        DER11("DER11","1:1关系"),
        WFSTEP("WFSTEP","工作流处理步骤"),
        WFSTEPACTOR("WFSTEPACTOR","工作流当前处理用户"),
        FILELIST("FILELIST","附件列表"),
        DATAAUDIT("DATAAUDIT","行为审计"),
        DERTYPE("DERTYPE","实体关系分组");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList14(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试状态]
     */
    @Getter
    public enum Test__status {
        WAIT("wait","未开始"),
        DOING("doing","进行中"),
        DONE("done","已完成"),
        BLOCKED("blocked","被阻塞");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Test__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[功能测试快速分组_测试单]
     */
    @Getter
    public enum CaseTestTaskQuickpachet {
        ALL("ALL","所有用例"),
        TOME("TOME","指派给我");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CaseTestTaskQuickpachet(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[ZT_IM_CONFERENCEACTION__TYPE]
     */
    @Getter
    public enum Im_conferenceaction__type {
        CREATE("create","create"),
        JOIN("join","join"),
        LEAVE("leave","leave"),
        CLOSE("close","close");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Im_conferenceaction__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[todo快速分组（我的地盘）]
     */
    @Getter
    public enum TodoQuickpacketMy {
        ALL("All","所有待办"),
        THISYEAR("thisyear","本年度"),
        UNFINISHED("Unfinished","未完"),
        BYME("BYME","待定"),
        CYCLE("cycle","周期");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        TodoQuickpacketMy(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[本地语言]
     */
    @Getter
    public enum CodeList56 {
        EN("EN","英文"),
        ZH_CN("ZH_CN","中文简体"),
        ZH_TW("ZH_TW","中文繁体（台湾）");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList56(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[项目类型]
     */
    @Getter
    public enum Project__type {
        SPRINT("sprint","短期项目"),
        WATERFALL("waterfall","长期项目"),
        OPS("ops","运维项目");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Project__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[输入辅助_页面参数]
     */
    @Getter
    public enum CodeList100 {
        CARETTEMPLGROUP_SRFDA_PAGEPARAM("CARETTEMPLGROUP_SRFDA_PAGEPARAM","常规页面参数"),
        CARETTEMPLGROUP_SRFDA_GRIDVIEWPAGEPARAM("CARETTEMPLGROUP_SRFDA_GRIDVIEWPAGEPARAM","表格页面参数"),
        CARETTEMPLGROUP_SRFDA_EDITVIEWPAGEPARAM("CARETTEMPLGROUP_SRFDA_EDITVIEWPAGEPARAM","编辑页面参数");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList100(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[时间周期]
     */
    @Getter
    public enum Zt__delta {
        ITEM_7("7","一星期"),
        ITEM_14("14","两星期"),
        ITEM_31("31","一个月"),
        ITEM_62("62","两个月"),
        ITEM_93("93","三个月"),
        ITEM_186("186","半年"),
        ITEM_365("365","一年");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="按时间段";

        Zt__delta(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[性能分析指标]
     */
    @Getter
    public enum CodeList117 {
        PODBACTION("PODBACTION","数据库操作性能"),
        PODBQUERY("PODBQUERY","数据库查询性能"),
        PODEDC("PODEDC","实体处理逻辑性能"),
        POWORKFLOW("POWORKFLOW","工作流性能"),
        POPAGE("POPAGE","页面性能"),
        POPAGEBACKEND("POPAGEBACKEND","页面性能(后台)"),
        POPAGESESSION("POPAGESESSION","并发性能");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList117(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[数据库触发器事件]
     */
    @Getter
    public enum CodeList66 {
        INSERT("INSERT","Insert"),
        UPDATE("UPDATE","Update"),
        DELETE("DELETE","Delete");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList66(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[是否]
     */
    @Getter
    public enum YesNo {
        ITEM_1("1","是"),
        ITEM_0("0","否");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        YesNo(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[发布快速分组]
     */
    @Getter
    public enum Realease_sort {
        ALL("all","所有"),
        NORMAL("normal","正常"),
        TERMINATE("terminate","停止维护");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Realease_sort(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[文档查询类型]
     */
    @Getter
    public enum DOCQTYPE {
        DOC("doc","文档"),
        DOCLIB("doclib","文档库"),
        MODULE("module","文档库分类");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        DOCQTYPE(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[需求评审结果（草稿）]
     */
    @Getter
    public enum Story__review_result_draft {
        PASS("pass","确认通过"),
        CLARIFY("clarify","有待明确"),
        REJECT("reject","拒绝");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Story__review_result_draft(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[属性预定义值规则]
     */
    @Getter
    public enum CodeList13 {
        INT("INT","整数"),
        POSITIVEINT("POSITIVEINT","正整数"),
        STRING("STRING","字符串"),
        STRING_EMAIL("STRING_EMAIL","电子邮件"),
        FLOAT("FLOAT","浮点数"),
        FLOAT_PERCENT("FLOAT_PERCENT","百分比数值(0~100)"),
        DATETIME("DATETIME","日期时间"),
        DATETIME_GTNOW("DATETIME_GTNOW","大于当天时间"),
        DATETIME_GTNOWNOHOUR("DATETIME_GTNOWNOHOUR","大于当天日期"),
        DATETIME_GTNOW3DAY("DATETIME_GTNOW3DAY","后3天");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList13(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[Bug严重程度]
     */
    @Getter
    public enum Bug__severity {
        ITEM_1(1,"致命"),
        ITEM_2(2,"严重"),
        ITEM_3(3,"一般"),
        ITEM_4(4,"轻微");

        private int value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Bug__severity(int value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[报表输出格式]
     */
    @Getter
    public enum CodeList54 {
        PDF("PDF","PDF"),
        EXCEL("EXCEL","EXCEL"),
        HTML("HTML","HTML");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList54(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试用例类型]
     */
    @Getter
    public enum Testcase__type {
        FEATURE("feature","功能测试"),
        PERFORMANCE("performance","性能测试"),
        CONFIG("config","配置相关"),
        INSTALL("install","安装部署"),
        SECURITY("security","安全相关"),
        INTERFACE("interface","接口测试"),
        UNIT("unit","单元测试"),
        OTHER("other","其他");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Testcase__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[月份（01～12）]
     */
    @Getter
    public enum DCodeList {
        ITEM_01("01","一月"),
        ITEM_02("02","二月"),
        ITEM_03("03","三月"),
        ITEM_04("04","四月"),
        ITEM_05("05","五月"),
        ITEM_06("06","六月"),
        ITEM_07("07","七月"),
        ITEM_08("08","八月"),
        ITEM_09("09","九月"),
        ITEM_10("10","十月"),
        ITEM_11("11","十一月"),
        ITEM_12("12","十二月");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        DCodeList(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试运行状态]
     */
    @Getter
    public enum Testrun__status {
        WAIT("wait","未开始"),
        DOING("doing","进行中"),
        DONE("done","已完成"),
        BLOCKED("blocked","被阻塞");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Testrun__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[文档内容类型]
     */
    @Getter
    public enum Doccontent__type {
        HTML("html","富文本"),
        MARKDOWN("markdown","Markdown"),
        URL("url","链接"),
        WORD("word","Word"),
        PPT("ppt","PPT"),
        EXCEL("excel","Excel");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Doccontent__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试结果]
     */
    @Getter
    public enum Testresult__result {
        N_A("n/a","忽略"),
        PASS("pass","通过"),
        FAIL("fail","失败"),
        BLOCKED("blocked","阻塞");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Testresult__result(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[文档权限]
     */
    @Getter
    public enum Doc__acl {
        OPEN("open","公开"),
        PRIVATE("private","私有"),
        CUSTOM("custom","自定义");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Doc__acl(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }




    /**
     * 代码表[报表分类]
     */
    @Getter
    public enum CodeList12 {
        REPORTFOLDER_1("REPORTFOLDER_1","经营性报表"),
        REPORTFOLDER_2("REPORTFOLDER_2","财务报表");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList12(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[任务种别]
     */
    @Getter
    public enum TaskSpecies {
        PLAN("plan","计划任务"),
        CYCLE("cycle","周期任务"),
        TEMP("temp","临时任务");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        TaskSpecies(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[用户数据行为]
     */
    @Getter
    public enum CodeList71 {
        ITEM_1("1","无建立"),
        ITEM_2("2","无更新"),
        ITEM_4("4","无删除"),
        ITEM_8("8","无查看");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList71(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[重新构建]
     */
    @Getter
    public enum RELEASE_rebuild {
        NONE(0,"无操作"),
        QUICK(1,"快速（删除本地项目与代码仓库多余文件）"),
        FIXMODEL(4,"修复模型"),
        FULL(2,"完整（完全重建本地项目及代码仓库）");

        private int value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        RELEASE_rebuild(int value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[汇报快速分组]
     */
    @Getter
    public enum REPORTQuickGroup {
        ALLCNT("All","所有"),
        DAILYCNT("daily","日报"),
        WEEKLYCNT("weekly","周报"),
        MONTHLYCNT("monthly","月报");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        REPORTQuickGroup(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[数据通知监控行为（新建、更新、删除）]
     */
    @Getter
    public enum CodeList98 {
        ITEM_1("1","新建"),
        ITEM_2("2","更新"),
        ITEM_4("4","删除");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList98(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[开发帮助重要程度]
     */
    @Getter
    public enum CodeList31 {
        LOW("LOW","低"),
        NORMAIL("NORMAIL","中"),
        HIGH("HIGH","高");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList31(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[产品访问控制]
     */
    @Getter
    public enum Product__acl {
        OPEN("open","默认设置(有产品视图权限，即可访问)"),
        PRIVATE("private","私有产品(相关负责人和项目团队成员才能访问)"),
        CUSTOM("custom","自定义白名单(团队成员和白名单的成员可以访问)");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Product__acl(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[产品快速分组]
     */
    @Getter
    public enum Product_quickpacket {
        ALL("All","所有"),
        NORMAL("normal","正常"),
        MORE("MORE","更多"),
        CLOSED("closed","结束"),
        BRANCH("branch","多平台"),
        PLATFORM("platform","多分支");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Product_quickpacket(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[用户模板快速分组]
     */
    @Getter
    public enum UserTplQuickGroup {
        ALL("all","所有"),
        STORY("story","需求"),
        TASK("task","任务"),
        BUG("bug","Bug");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        UserTplQuickGroup(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[需求来源]
     */
    @Getter
    public enum Story__source {
        CUSTOMER("customer","客户"),
        USER("user","用户"),
        PO("po","产品经理"),
        MARKET("market","市场"),
        SERVICE("service","客服"),
        OPERATION("operation","运营"),
        SUPPORT("support","技术支持"),
        COMPETITOR("competitor","竞争对手"),
        PARTNER("partner","合作伙伴"),
        DEV("dev","开发人员"),
        TESTER("tester","测试人员"),
        BUG("bug","Bug"),
        FORUM("forum","论坛"),
        OTHER("other","其它"),
        IBIZ("iBiz","iBiz");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Story__source(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }




    /**
     * 代码表[页面样式]
     */
    @Getter
    public enum CodeList22 {
        ITEM_0("0","跳转处理界面"),
        ITEM_1("1","数据编辑界面"),
        ITEM_2("2","数据表格界面"),
        ITEM_9("9","数据树形界面"),
        ITEM_3("3","数据选择界面"),
        ITEM_4("4","工作流数据编辑界面"),
        ITEM_5("5","工作流表格界面"),
        ITEM_6("6","工作流管理表格界面"),
        ITEM_7("7","导航界面"),
        ITEM_8("8","信息展示界面");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList22(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[产品快速分组]
     */
    @Getter
    public enum ProductQuickpacket {
        ALL("All","所有"),
        NORMAL("normal","未关闭"),
        CLOSED("closed","已关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        ProductQuickpacket(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[页面资源类型]
     */
    @Getter
    public enum CodeList17 {
        NONE("NONE","无资源"),
        DEDATA("DEDATA","数据操作"),
        PAGE("PAGE","页面对象"),
        CUSTOM("CUSTOM","自定义");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList17(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[月（1～31）]
     */
    @Getter
    public enum CodeList47 {
        ITEM_1("1","1号"),
        ITEM_2("2","2号"),
        ITEM_3("3","3号"),
        ITEM_4("4","4号"),
        ITEM_5("5","5号"),
        ITEM_6("6","6号"),
        ITEM_7("7","7号"),
        ITEM_8("8","8号"),
        ITEM_9("9","9号"),
        ITEM_10("10","10号"),
        ITEM_11("11","11号"),
        ITEM_12("12","12号"),
        ITEM_13("13","13号"),
        ITEM_14("14","14号"),
        ITEM_15("15","15号"),
        ITEM_16("16","16号"),
        ITEM_17("17","17号"),
        ITEM_18("18","18号"),
        ITEM_19("19","19号"),
        ITEM_20("20","20号"),
        ITEM_21("21","21号"),
        ITEM_22("22","22号"),
        ITEM_23("23","23号"),
        ITEM_24("24","24号"),
        ITEM_25("25","25号"),
        ITEM_26("26","26号"),
        ITEM_27("27","27号"),
        ITEM_28("28","28号"),
        ITEM_29("29","29号"),
        ITEM_30("30","30号"),
        ITEM_31("31","31号");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList47(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[数据通知类型]
     */
    @Getter
    public enum CodeList61 {
        TIME("TIME","定时"),
        NORMAL("NORMAL","值变更"),
        TIMEEX("TIMEEX","定时+值判断");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList61(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[实体数据操作步骤]
     */
    @Getter
    public enum CodeList106 {
        GETDEFAULT("GETDEFAULT","获取默认值"),
        BEFORESAVE("BEFORESAVE","保存之前"),
        AFTERSAVE("AFTERSAVE","保存之后"),
        BEFOREREMOVE("BEFOREREMOVE","删除之前"),
        AFTERREMOVE("AFTERREMOVE","删除之后"),
        TESTSAVE("TESTSAVE","测试保存"),
        CUSTOMCALL("CUSTOMCALL","自定义操作"),
        INTERNALCALL("INTERNALCALL","内部调用");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList106(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }




    /**
     * 代码表[数据同步方向]
     */
    @Getter
    public enum CodeList97 {
        IN("IN","输入"),
        OUT("OUT","输出");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList97(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[过期计划]
     */
    @Getter
    public enum ExpiredPlan {
        ITEM_1("1","过期计划");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        ExpiredPlan(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[Bug快速分组（版本所有bug）]
     */
    @Getter
    public enum BugCodeList2 {
        ALL("All","所有"),
        ACTIVE("active","未解决");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        BugCodeList2(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[表格列对齐]
     */
    @Getter
    public enum CodeList18 {
        LEFT("left","左对齐"),
        CENTER("center","剧中"),
        RIGHT("right","右对齐");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList18(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[数据通知取值规则]
     */
    @Getter
    public enum CodeList69 {
        AFTER("AFTER","变更后"),
        BEFORE("BEFORE","变更前"),
        CHANGE("CHANGE","值变更");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList69(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[开发数据版本控制状态]
     */
    @Getter
    public enum CodeList115 {
        CHECKIN("CHECKIN","签入"),
        CHECKOUT("CHECKOUT","签出");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList115(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[页面参数值变量]
     */
    @Getter
    public enum CodeList63 {
        PARAM1("PARAM1","参数1(字符)"),
        PARAM2("PARAM2","参数2(字符)");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList63(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }




    /**
     * 代码表[表格每页记录数]
     */
    @Getter
    public enum CodeList26 {
        ITEM_10("10","10行"),
        ITEM_20("20","20行"),
        ITEM_30("30","30行"),
        ITEM_40("40","40行"),
        ITEM_50("50","50行"),
        ITEM_60("60","60行"),
        ITEM_70("70","70行"),
        ITEM_80("80","80行"),
        ITEM_90("90","90行"),
        ITEM_100("100","100行");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList26(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[Bug类型]
     */
    @Getter
    public enum Bug__type {
        CODEERROR("codeerror","代码错误"),
        CONFIG("config","配置相关"),
        INSTALL("install","安装部署"),
        SECURITY("security","安全相关"),
        PERFORMANCE("performance","性能问题"),
        STANDARD("standard","标准规范"),
        AUTOMATION("automation","测试脚本"),
        DESIGNDEFECT("designdefect","设计缺陷"),
        OTHERS("others","其他");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Bug__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[界面功能类型]
     */
    @Getter
    public enum CodeList91 {
        INHERIT("INHERIT","继承模板"),
        DEFAULT("DEFAULT","默认功能"),
        CUSTOM("CUSTOM","自定义");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList91(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[关闭产品]
     */
    @Getter
    public enum CloseProduct {
        ITEM_1("1","关闭产品");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CloseProduct(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[任务快速分组]
     */
    @Getter
    public enum Task_quickpacket {
        ALLTASKCNT("All","所有"),
        UNCLOSETASKCNT("UNCLOSED","未关闭"),
        ASSTOMYTASKCNT("TOME","指派给我"),
        STORYCHANGECNT("storychange","需求变更"),
        MORETASKCNT("MORE","更多"),
        UNSTARTTASKCNT("NOTSTARTED","未开始"),
        YSTARTASKCNT("INPROGRESS","进行中"),
        UNCOMPLETETASKCNT("UNACCOMPLISHED","未完成"),
        MYCOMPLETETASKCNT("IFINISHED","我完成"),
        YCOMPLETETASKCNT("COMPLETED","已完成"),
        CLOSETASKCNT("CLOSED","已关闭"),
        CANCELTASKCNT("CANCELLED","已取消");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Task_quickpacket(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[日小时（0～23）]
     */
    @Getter
    public enum CodeList85 {
        ITEM_00("00","0点"),
        ITEM_01("01","1点"),
        ITEM_02("02","2点"),
        ITEM_03("03","3点"),
        ITEM_04("04","4点"),
        ITEM_05("05","5点"),
        ITEM_06("06","6点"),
        ITEM_07("07","7点"),
        ITEM_08("08","8点"),
        ITEM_09("09","9点"),
        ITEM_10("10","10点"),
        ITEM_11("11","11点"),
        ITEM_12("12","12点"),
        ITEM_13("13","13点"),
        ITEM_14("14","14点"),
        ITEM_15("15","15点"),
        ITEM_16("16","16点"),
        ITEM_17("17","17点"),
        ITEM_18("18","18点"),
        ITEM_19("19","19点"),
        ITEM_20("20","20点"),
        ITEM_21("21","21点"),
        ITEM_22("22","22点"),
        ITEM_23("23","23点");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList85(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[月份（1～12）]
     */
    @Getter
    public enum CodeList82 {
        ITEM_01("01","1月"),
        ITEM_02("02","2月"),
        ITEM_03("03","3月"),
        ITEM_04("04","4月"),
        ITEM_05("05","5月"),
        ITEM_06("06","6月"),
        ITEM_07("07","7月"),
        ITEM_08("08","8月"),
        ITEM_09("09","9月"),
        ITEM_10("10","10月"),
        ITEM_11("11","11月"),
        ITEM_12("12","12月");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList82(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[数据通知监控行为]
     */
    @Getter
    public enum CodeList72 {
        ITEM_1("1","新建"),
        ITEM_2("2","更新"),
        ITEM_3("3","新建或更新"),
        ITEM_4("4","删除");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList72(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[实体规则处理_值处理函数]
     */
    @Getter
    public enum CodeList110 {
        DATE_DIFF_D("DATE_DIFF_D","距今天数（日期）"),
        DATE_DIFF_W("DATE_DIFF_W","距今周数（日期）"),
        DATE_DIFF_M("DATE_DIFF_M","距今月份数（日期）"),
        DATE_DIFF_Q("DATE_DIFF_Q","距今季度数（日期）"),
        DATE_DIFF_Y("DATE_DIFF_Y","距今年数（日期）");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList110(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[计划模板类型]
     */
    @Getter
    public enum PlantempletType {
        STEP("step","计划"),
        GROUP("group","父计划"),
        ITEM("item","子计划");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        PlantempletType(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[测试用例结果]
     */
    @Getter
    public enum Testcase__result {
        N_A("n/a","忽略"),
        PASS("pass","通过"),
        FAIL("fail","失败"),
        BLOCKED("blocked","阻塞");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="未执行";

        Testcase__result(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[Bug类型（饼图）]
     */
    @Getter
    public enum Bug_type_pie {
        CODEERROR("codeerror","代码错误"),
        CONFIG("config","配置相关"),
        INSTALL("install","安装部署"),
        SECURITY("security","安全相关"),
        PERFORMANCE("performance","性能问题"),
        STANDARD("standard","标准规范"),
        AUTOMATION("automation","测试脚本"),
        DESIGNDEFECT("designdefect","设计缺陷"),
        OTHERS("others","其他"),
        ITEM_10("无","无");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Bug_type_pie(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[是否（数值）]
     */
    @Getter
    public enum YesNo2 {
        ITEM_1(1,"是"),
        ITEM_0(0,"否");

        private int value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        YesNo2(int value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[全文检索类型]
     */
    @Getter
    public enum IndexType {
        BUG("bug","Bug"),
        PRODUCT("product","产品"),
        TASK("task","任务"),
        CASE("case","功能测试"),
        DOC("doc","文档"),
        STORY("story","需求"),
        PROJECT("project","项目");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        IndexType(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[汇报状态]
     */
    @Getter
    public enum ReportStatus {
        ITEM_0("0","未读"),
        ITEM_1("1","已读");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        ReportStatus(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[产品类型]
     */
    @Getter
    public enum Product__type {
        NORMAL("normal","正常"),
        BRANCH("branch","多分支"),
        PLATFORM("platform","多平台");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Product__type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[项目状态]
     */
    @Getter
    public enum Project__status {
        WAIT("wait","未开始"),
        DOING("doing","进行中"),
        SUSPENDED("suspended","已挂起"),
        CLOSED("closed","已关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Project__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试用例状态（表格）]
     */
    @Getter
    public enum TestCaseStatusGrid {
        WAIT("wait","未开始"),
        NORMAL("normal","正常"),
        BLOCKED("blocked","被阻塞"),
        INVESTIGATE("investigate","研究中"),
        DONE("done","已完成"),
        STORYCHANGE("storychange","需求变更"),
        CASECHANGE("casechange","原用例更新");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        TestCaseStatusGrid(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }





    /**
     * 代码表[Bug操作系统]
     */
    @Getter
    public enum Bug__os {
        ALL("all","全部"),
        WINDOWS("windows","Windows"),
        WIN10("win10","Windows 10"),
        WIN8("win8","Windows 8"),
        WIN7("win7","Windows 7"),
        VISTA("vista","Windows Vista"),
        WINXP("winxp","Windows XP"),
        WIN2012("win2012","Windows 2012"),
        WIN2008("win2008","Windows 2008"),
        WIN2003("win2003","Windows 2003"),
        WIN2000("win2000","Windows 2000"),
        ANDROID("android","Android"),
        IOS("ios","IOS"),
        WP8("wp8","WP8"),
        WP7("wp7","WP7"),
        SYMBIAN("symbian","Symbian"),
        LINUX("linux","Linux"),
        FREEBSD("freebsd","FreeBSD"),
        OSX("osx","OS X"),
        UNIX("unix","Unix"),
        OTHERS("others","其他");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Bug__os(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[Bug状态]
     */
    @Getter
    public enum Bug__status {
        ACTIVE("active","激活"),
        RESOLVED("resolved","已解决"),
        CLOSED("closed","已关闭");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Bug__status(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[ZT_IM_MESSAGE__CONTENTTYPE]
     */
    @Getter
    public enum Im_message__contentType {
        TEXT("text","text"),
        PLAIN("plain","plain"),
        EMOTION("emotion","emotion"),
        IMAGE("image","image"),
        FILE("file","file"),
        OBJECT("object","object"),
        CODE("code","code");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Im_message__contentType(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[待办类型]
     */
    @Getter
    public enum Type {
        CUSTOM("custom","自定义"),
        BUG("bug","Bug"),
        TASK("task","项目任务"),
        STORY("story","项目需求");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Type(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[实体数据处理_变量名称]
     */
    @Getter
    public enum CodeList109 {
        _DEFAULT_("%DEFAULT%","默认变量"),
        _ENV_("%ENV%","环境变量"),
        PARAM1("PARAM1","变量1"),
        PARAM2("PARAM2","变量2"),
        PARAM3("PARAM3","变量3"),
        PARAM4("PARAM4","变量4"),
        PARAM5("PARAM5","变量5"),
        _LAST_("%LAST%","历史值"),
        _GLOBAL1_("%GLOBAL1%","全局变量1"),
        _GLOBAL2_("%GLOBAL2%","全局变量2"),
        _GLOBAL3_("%GLOBAL3%","全局变量3"),
        _GLOBAL4_("%GLOBAL4%","全局变量4"),
        _GLOBAL5_("%GLOBAL5%","全局变量5"),
        _BRINST_("%BRINST%","全局规则引擎实例变量");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList109(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[汇报类型]
     */
    @Getter
    public enum ReportType {
        WEEKLY("weekly","周报"),
        DAILY("daily","日报"),
        MONTHLY("monthly","月报"),
        REPORTLY("reportly","汇报");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        ReportType(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[配置范围]
     */
    @Getter
    public enum ConfigScope {
        SYS("sys","全局"),
        ORG("org","当前组织"),
        DEPT1("dept1","当前部门（含子部门）"),
        DEPT2("dept2","当前部门（不含子部门）"),
        USER("user","个人");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        ConfigScope(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[字段排序方向]
     */
    @Getter
    public enum SortDir {
        ASC("ASC","升序"),
        DESC("DESC","降序");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        SortDir(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[日历参与者状态]
     */
    @Getter
    public enum CodeList15 {
        UNDECIDED("UNDECIDED","未确定"),
        ACCEPT("ACCEPT","接受"),
        REJECT("REJECT","拒绝");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList15(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[日期选择]
     */
    @Getter
    public enum Datecheckbox {
        ON("on","待定");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Datecheckbox(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[来源对象]
     */
    @Getter
    public enum Ibizpro_story__sourceobject {
        SOURCENOTE__DATAENTITY("sourcenote__dataentity","实体");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Ibizpro_story__sourceobject(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[测试执行结果]
     */
    @Getter
    public enum Testrun__result {
        N_A("n/a","忽略"),
        PASS("pass","通过"),
        FAIL("fail","失败"),
        BLOCKED("blocked","阻塞");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        Testrun__result(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }



    /**
     * 代码表[实体数据库操作]
     */
    @Getter
    public enum CodeList53 {
        INSERT("INSERT","插入"),
        UPDATE("UPDATE","更新"),
        SELECT("SELECT","查询"),
        DELETE("DELETE","删除"),
        CUSTOM("CUSTOM","自定义");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList53(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[扩展表格单元格边框样式]
     */
    @Getter
    public enum CodeList76 {
        LEFT("LEFT","左边框"),
        TOP("TOP","上边框"),
        RIGHT("RIGHT","右边框"),
        BOTTOM("BOTTOM","下边框");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        CodeList76(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }


    /**
     * 代码表[配置分组]
     */
    @Getter
    public enum ConfigGroup {
        GROUP("GROUP","分组"),
        ITEM("ITEM","配置项");

        private String value;
        private String text;
        private String valueSeparator="";
        private String textSeparator="";
        private String emptyText="";

        ConfigGroup(String value , String text) {
            this.value=value;
            this.text = text;
        }
    }

}

