function getLocaleResourceBase(){
    const data:any = {
        "404": {
            errortext1: '抱歉，您访问的页面不存在！',
            errortext2: '您要找的页面不存在，请返回',
            indexpage: '首页',
            continue: '继续浏览',
        },
        "500": {
            errortext1: '抱歉，服务器出错了！',
            errortext2: '服务器出错了，请返回 ',
            indexpage: '首页',
            continue: '继续浏览',
        },
        apporgsector:{
          successswitch:'切换成功',
          errorswitch:'切换失败',
        },
        appautocomplete: {
            error: '错误',
            miss: '缺少参数',
            requestexception: '请求异常！',
        },
        appbuild: {
          custom: '定制',
        },
        appcolumnlink: {
          error: '错误',
          valueitemexception:"值项异常",
          rowdataexception:"表格行数据异常",
        },
        appcolumnrender: {
          select: '请选择...',
          unsupported: '不支持',
        },
        microcom: {
          filterwarn: "filter参数配置错误, 请检查!"
        },
        appdashboarddesign: {
          global: '全局',
        },
        appdatauploadview: {
          "selectfile":"单击此区域进行上传",
          "uploadserver":"上传数据",
          "datatemplate":"导入数据模板",
          "datatemplatemessage":"下载导入模版，并按要求填写：",
          "read":"读取成功",
          "completed":"导入完成",
          "confirm":"确认",
          "cancel":"取消",
          "importfailed":"导入失败"
        },
        appdebugactions:{
          button: '开启配置模式',
        },
        appexportexcel: {
          total: '全部',
          max: '最大',
          row: '行',
          currentpage: '当前页',
          desc:'请输入起始页',
          desc1:'请输入有效的起始页',
        },
        appfileupload: {
          preview: '查看',
          uploadtext: '将文件拖到此处，或<em>点击上传</em>',
          uploaderror: '上传失败',
          filetypeerrortitle: '文件类型错误',
          filetypeerrorinfo: '请选择图片类型的文件，如JPEG，GIF，PNG，BMP',
          downloaderror: "图片下载失败！",
          caption: "上传",
        },
        camera: {
          dev: "开发中",
          choose: "摄像头选择：",
          left: "左旋转",
          right: "右旋转",
          photo: "拍照",
          save: "保存"
        },
        group2: {
          noreal: "未实现"
        },
        part: {
          error: "加载动态表单模型数据异常"
        },
        scren: {
          all: "应用全屏"
        },
        appformdruipart: {
          blockuitipinfo: '请先保存主数据',
          viewloadcomp:'多数据视图加载完成，触发后续表单项更新',
          save:'关系数据保存完成',
          change:'关系数据值变化',
          change1:'视图数据变化',
          loadcomp:'视图加载完成',
        },
        appheadermenus: {
          ibizlab:{
              title: 'iBiz开放平台',
          },
          publishproject:{
              title: '项目文件',
          },
          ibizstudio:{
              title: '模型设计工具',
          },
          ibizbbs:{
              title: 'iBiz论坛',
          },
        },
        appmpicker: {
          error: '错误',
          miss: '缺少参数',
          requestexception: '请求异常！',
        },
        apppicker: {
          error: '错误',
          miss: '缺少参数',
          requestexception: '请求异常！',
          newandedit: '创建并编辑...',
          systemexception: '系统异常！',
          valueitemexception: '值项异常！',
          formdataexception: '表单数据异常！',
          nosupport: "重定向视图暂未支持"
        },
        apppickerselectview: {
          error: '错误',
          valueitemexception: '值项异常！',
          formdataexception: '表单数据异常！',
          placeholder: '请选择...',
        },
        appportaldesign: {
          customportal: '自定义门户',
          recover: "恢复默认",
          save: '保存',
        },
        apprangdate: {
          placeholder: '请选择时间...',
          from: '自',
          daystart: '日 0 时 起 至',
          dayend: '日 24 时 止',
        },
        apprangeeditor: {
          placeholder: '请选择时间...',
          input: '请输入...',
        },
        appdaterange: {
          start: '开始时间',
          end: '结束时间',
          separator: '至'
        },
        appstudioaction: {
          configtitle: '进入当前视图配置界面',
          configbutton: '配置',
          issuetitle: '建立当前界面的issues',
          issuebutton: '新建issues',
          success: "拷贝成功!",
          copyname: "点击拷贝视图名称",
          view: "查看"
        },
        apptreepicker: {
          placeholder: '请选择...',
        },
        daterange: {
          starttext: '保险期限 ： 自',
          endtext: '日 24 时 止',
          startplaceholder: '开始日期',
          rangeseparatorr: ' 0 时起 至',
          endplaceholder: '结束日期',
        },
        dropdownlist: {
          placeholder: '请选择...',
          valueerror: "下拉列表，值转换失败",
        },
        dropdownlistdynamic: {
          placeholder: '请选择...',
        },
        dropdownlistmpicker: {
          placeholder: '请选择...',
        },
        login: {
          error: '错误',
          caption: '欢迎登录',
          placeholder1:'用户名',
          placeholder2:'密码',
          name: '登录',
          reset:'重置',
          other:'其他登录方式',
          tip: '输入用户名和密码',
          warning1:'qq授权登录暂未支持',
          warning2:'微信授权登录暂未支持',
          loginname: {
              placeholder: '请输入用户名',
              message: '用户名不能为空',
          },
          password: {
              placeholder: '请输入密码',
              message: '密码不能为空',
          },
          loginfailed: '登录失败',
        },
        appuser: {
          name: '系统管理员',
          logout: '退出登录',
          surelogout: '确认要退出登录？',
          changepwd: "修改密码",
        },
        apptheme: {
          caption: {
            theme: '主题',
            font: '字体',
          },
          fontfamilys: {
              MicrosoftYaHei: '微软雅黑',
              SimHei: '黑体',
              YouYuan: '幼圆',
          },
          config: "主题配置",
          customtheme: "自定义主题",
          color: "主题色",
          preview: "预览",
          save: "保存配置",
          reset: "重置",
          success: {
            savethemeoption: "保存自定义主题成功",
            copyurl: '复制分享链接成功'
          },
          share: "分享",
          error: {
            getshareurl: "获取分享主题配置失败",
            generateshareurl: "生成分享链接失败"
          },
          configbutton: '复制',
          createurl: '已创建分享链接',
          applytheme: '已应用分享主题',
        },
        appformgroup: {
          hide: '隐藏字段',
          showmore: '显示更多字段',
        },
        appupdatepassword: {
          oldpwd: '原密码',
          newpwd: '新密码',
          confirmpwd: '确认密码',
          sure: '确认修改',
          oldpwderr: '原密码不能为空！',
          newpwderr: '新密码不能为空！',
          confirmewderr: '两次输入密码不一致！',
        },
        appaddressselection: {
          loaddatafail: '城市数据加载失败'
        },
        appgroupselect:{
          groupselect:'分组选择', 
        },
        appimageupload:{
          uploadfail:'上传失败'
        },
        apporgselect:{
          loadfail:'加载数据失败',
          reseterror: "重置应用数据出现异常"
        },
        apptransfer:{
          title1:'未选择',
          title2:'已选择',
        },
        appwfapproval:{
          commit:'提交',
          wait:'等待',
          handle:'处理',
          placeholder:'请输入内容',
          end:'结束'
        },
        contextmenudrag:{
          allapp:'全部应用',
          nofind:'未找到该应用',
          error: "加载数据错误",
          portlet: "企业门户"
        },
        filtermode:{
          placeholder:'条件逻辑',  
        },
        filtertree:{
          title1:'添加条件',
          title2:'添加组',
          placeholder:'属性', 
        },
        ibizgrouppicker:{
          ok:'确认',
          cancel:'取消',
        },
        ibizgroupselect:{
          groupselect:'分组选择'
        },
        tabpageexp:{
          more:'更多',
        },
        uploadfile:{
          imgmsg:'将图片拖到这里替换',
          localupload:'本地上传',
          or:'或',
          imgmsg1:'从素材库选择',
          choose: "选择上传文件"
        },
        lockscren:{
          title:'设置锁屏密码',
          label:'锁屏密码',
          message:'锁屏密码不能为空',
          placeholder:'请输入锁屏密码',
          placeholder1:'请输入登录密码',
          message1: '解锁密码错误,请重新输入',
          promptInformation:'是否退出系统, 是否继续?',
          prompt:'提示',
          confirmbuttontext:'确定',
          cancelbuttontext:'取消',
          lock: "应用锁屏"
        },
        croneditor: {
          label: {
            second: '秒',
            minute: '分',
            hour: '时',
            day: '日',
            week: '周',
            month: '月',
            year: '年',
          },
          public : {
            specify: '指定',
            notspecify: '不指定',
            cycle: '周期',
            from: '从',
            to: '至',
            loop: '循环',
            every: '每',
            once: '执行一次',
          },
          second: '秒',
          minute: '分',
          hour: {
            title: '时',
            everyhour: '每时',
            hourstart: '时开始，每',
            onceahour: '时执行一次',
          },
          day: {
            title: '日',
            daily: '每日',
            workday: '工作日',
            daystart: '日开始，每',
            onceaday: '日执行一次',
            thismonth: '本月',
            lastworkday: '号，最近的工作日',
            lastdayofmonth: '本月最后一天',
          },
          month: {
            title: '月',
            everymonth: '每月',
            monthstart: '月开始，每',
            onceamonth: '月执行一次',
          },
          week: {
            title: '周',
            everyweek: '每周',
            from: '从星期',
            to: '至星期',
            start: '开始，每',
            onceaday: '天执行一次',
            specifyweek: '指定周',
            weekofmonth: '本月第',
            weekweek: '周，星期',
            lastofmonth: '本月最后一个',
            week: '星期',
          },
          year: {
            title: '年',
            everyyear: '每年',
          },
          message: {
            error1: '日期与星期不可以同时为“不指定”',
            error2: '日期与星期必须有一个为“不指定”',
          },
        },
        appmessagepopover: {
          loadmore: '加载更多',
          nomore: '没有更多了',
          error: "加载数据错误",
          geterror: "未获取到标签内容",
          errorreturn: "事件触发源无值，强制返回",
          myTasksLabel:'待办',
          myMsgsLabel:'消息',
        },
        appmessagebox: {
          ok: "确 认",
          cancel: "取 消"
        },
        diskFileUpload:{
          fileDrag: '将文件拖到此处，或',
          clickUpload: '点击上传',
          load: '下载',
          preview: '预览',
          edit: '编辑',
          delete: '删除',
          clues: '单个文件大小不超过',
          clues1: '文件不超过',
          getFileFailure: '获取文件列表失败',
          loadFailure: '上传文件失败',
          loadFailure1: '上传失败,单个文件不得超过',
          downloadFile: '下载文件失败',
          downloadFile1: '下载文件失败,未获取到文件!',
          deleteFile: '此操作将永久删除该文件, 是否继续?',
          deleteFilePrompt: '提示',
          true: '确定',
          false: '取消',
          deleteFileFailure: '删除文件失败',
          updateFailure: '批量更新文件失败',
        },
        diskimageupload:{
          preview: '预览',
          ocrdiscern: 'ORC识别',
          load: '下载',
          delete: '删除',
          getimagefailure: '获取图片列表失败',
          loadimagefailure: '下载缩略图失败',
          loadimagefailure1: '下载缩略图失败,未获取到文件!',
          loadimagefailure2:'下载图片失败',
          loadimagefailure3:'下载图片失败,未找到图片!',
          imageidnone: '图片id不存在!',
          uploadimagefailure: '上传图片失败',
          uploadimagefailure1: '上传失败,仅支持＇gif，jpg，png，bmp＇格式的图片!',
          uploadfailure: '上传失败,单个图片不得超过',
          notimageurl: '图片url不存在',
          deletefile: '此操作将永久删除该文件, 是否继续?',
          deletefileprompt: '提示',
          true: '确定',
          false: '取消',
          deleteimagefailure:'删除图片失败',
          updatefailure: '批量更新文件失败',
        },
        appmapposition: {
          submit: '确认',
          title: '请选择地址',
          address: "四川省成都市成华区猛追湾街道四川电视塔天府熊猫塔",
          city: "成都"
        },
        appsortbar: {
          title: '排序'
        },
        appaftertime:{
          minutesago: '分钟前',
          hoursago: '小时前',
          dayago: '天前',
          monthsago: '月前',
          yearsago: '年前'
        },
        timeline: {
          index: "序号",
          node: "节点",
          author: "办理人员",
          type: "操作",
          lasttime: "完成时间",
          opinion: "审批意见",
          inhand: "待办理:",
        },
        content: {
          title: "「打开/关闭」底部分页 [ctrl + `]",
          open: "展开菜单",
          close: "收起菜单"
        },
        richtext: {
          content: "请输入模板内容",
          title: "请输入模板标题",
          save: "保存模板",
          pub: "公开的",
          pri: "私人的",
          apply: "应用模板",
          success: "保存模板成功!!!",
          error: "保存模板失败!!!",
          delsuccess: "删除模板成功!!!",
          delerror: "删除模板失败!!!"
        },
        search: {
          holder: "搜索内容"
        },
        drawer: {
          back: "返回",
          confirm: "确认关闭所有界面?",
          close: "关闭所有视图",
          toperror: "上飘窗打开视图参数转换异常"
        },
        appsharepage: {
          apply: '应用',
          cancel: '取消',
          invite: '邀请您加入主题应用!'
        }
    };
    return data;
  }
  export default getLocaleResourceBase;