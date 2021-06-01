function getLocaleResourceBase(){
    const data:any = {
        "404": {
            errortext1: 'sorry，the page you visited is not exist！',
            errortext2: 'The page you visited is not exist，please return to',
            indexpage: 'home page',
            continue: 'to continue browsing',
        },
        "500": {
            errortext1: "sorry，there's something wrong with the server！",
            errortext2: "There's something wrong with the server，please return to",
            indexpage: 'home page',
            continue: 'to continue browsing',
        },
        apporgsector:{
          successswitch:'The switch was successful',
          errorswitch:'The switchover failed',
        },
        appautocomplete: {
            error: 'Error',
            miss: 'Missing parameter ',
            requestexception: 'Request Exception！',
        },
        appbuild: {
            custom: 'Customize',
        },
        appcolumnlink: {
          error: 'error',
          valueitemexception:"value item exception",
          rowdataexception:"table row data exception",
        },
        appcolumnrender: {
          select: 'please select...',
          unsupported: 'unsupported',
        },
        microcom: {
          filterWarn: "Filter parameter configuration error, please check!"
        },
        appdashboarddesign: {
          global: 'Global',
        },
        appdatauploadview: {
          "selectfile":"Click this area to upload",
          "uploadserver":"Upload Server",
          "datatemplate":"Import Data Template",
          "datatemplatemessage":"Download the Import Data Template, According to the specification required to fill in",
          "read":" read Successful",
          "completed":"Import Completed",
          "confirm":"OK",
          "cancel":"Cancel",
          "importfailed":"Import Failed"
        },
        appdebugactions: {
          button: 'Open Configuration Mode',
        },
        appexportexcel: {
          total: 'All',
          max: 'At Most',
          row: 'Lines',
          currentpage: 'Current Page',
          desc:'Please enter the start page',
          desc1:'Please enter a valid start page',
        },
        appfileupload: {
          preview: 'preview',
          uploadtext: 'Drag files here，or <em>Click</em> to upload',
          filetypeerrortitle: 'File type incorrect',
          filetypeerrorinfo: 'Please select files with picture types，such as JPEG，GIF，PNG，BMP',
          downloaderror: "Image download failed!",
          caption: "upload",
        },
        camera: {
          dev: "developing",
          choose: "Camera selection:",
          left: "anticlockwise",
          right: "right rotation",
          photo: "photograph",
          save: "save"
        },
        group2: {
          noreal: "unrealized"
        },
        part: {
          error: "Load dynamic form model data exception"
        },
        scren: {
          all: "Application of full screen"
        },
        appformdruipart: {
          blockuitipinfo: 'Please save the major data first',
          viewloadcomp:'After the multi data view is loaded, the subsequent form item update will be triggered',
          save:'Relationship data save complete',
          change:'Relationship data value change',
          change1:'View data changes',
          loadcomp:'View loading complete',
        },
        appheadermenus: {
          ibizlab:{
              title: 'iBizLab',
          },
          publishproject:{
              title: 'Publish Project',
          },
          ibizstudio:{
              title: 'Model Design Tools',
          },
          ibizbbs:{
              title: 'iBizBBS',
          },
        },
        appmpicker: {
          error: 'Error',
          miss: 'Missing parameter ',
          requestexception: 'Request Exception！',
        },
        apppicker: {
          error: 'Error',
          miss: 'Missing parameter ',
          requestexception: 'Request Exception！',
          newandedit: 'Create And Edit...',
          systemexception: 'System Error！',
          valueitemexception: 'valueitem Error！',
          formdataexception: 'formdata Error！',
          nosupport: "The redirect view is not yet supported"
        },
        apppickerselectview: {
          error: 'Error',
          valueitemexception: 'valueitem Error！',
          formdataexception: 'formdata Error！',
          placeholder: 'Please select...',
        },
        appportaldesign: {
          customportal: 'Custom portal',
          recover: "restore default",
          save: 'Save',
        },
        apprangdate: {
          placeholder: 'Please select time...',
          from: 'from',
          daystart: '00:00:00 to',
          dayend: '24:00:00',
        },
        apprangeeditor: {
          placeholder: 'Please select time...',
          input: 'Please input...',
        },
        appstudioaction: {
          configtitle: 'Enter the configuration of current view',
          configbutton: 'Configuration',
          issuetitle: 'Create issues of current view',
          issuebutton: 'Create issues',
          success: "Copy success!",
          copyname: "Click copy view name",
          view: "check"
        },
        apptreepicker: {
          placeholder: 'please select...',
        },
        daterange: {
          starttext: 'Insurance period ： From',
          endtext: '24:00:00',
          startplaceholder: 'Begin Date',
          rangeseparatorr: ' 00:00:00 To',
          endplaceholder: 'End Dat4e',
        },
        dropdownlist: {
          placeholder: 'please select...',
          valueerror: "Drop down list, value conversion failed",
        },
        dropdownlistdynamic: {
          placeholder: 'please select...',
        },
        dropdownlistmpicker: {
          placeholder: 'please select...',
        },
        login: {
          error: 'Error',
          caption: 'Welcome to login',
          placeholder1:'User name',
          placeholder2:'Password',
          name: 'Login',
          reset:'Reset',
          other:'Other login methods',
          tip: 'Enter username and password',
          warning1:'QQ authorization login not supported',
          warning2:'Wechat authorized login not supported',
          loginname: {
              placeholder: 'Username',
              message: 'The username cannot be empty',
          },
          password: {
              placeholder: 'Password',
              message: 'The password cannot be empty',
          },
          loginfailed: 'Login failed',
        },
        appuser: {
          name: 'System',
          logout: 'Logout',
          surelogout: 'Are you sure logout?',
          changepwd: "Change Password",
        },
        apptheme: {
          caption: {
            theme: 'Theme',
            font: 'Font family',
          },
          fontfamilys: {
              MicrosoftYaHei: 'Microsoft YaHei',
              SimHei: 'SimHei',
              YouYuan: 'YouYuan',
          },
          config: "The topic configuration",
          customtheme: "Custom Theme",
          color: "subject color",
          preview: "preview",
          save: "Save the configuration",
          reset: "reset",
          success: "Save custom theme successfully"
        },
        appformgroup: {
          hide: 'hide',
          showmore: 'show more',
        },
        appupdatepassword: {
          oldpwd: 'Original password',
          newpwd: 'New password',
          confirmpwd: 'Confirm password',
          sure: 'Confirm modification',
          oldpwderr: 'The original password cannot be empty!',
          newpwderr: 'New password cannot be empty!',
          confirmewderr: 'The two input passwords are inconsistent!',
        },
        appaddressselection: {
          loaddatafail: 'City data loading failed'
        },
        appGroupSelect:{
          groupSelect:'Group selection', 
        },
        appimageupload:{
          uploadfail:'Upload failed'
        },
        apporgselect:{
          loadfail:'Failed to load data',
          reseterror: "An exception occurred in resetting application data"
        },
        appTransfer:{
          title1:'Not selected',
          title2:'Selected',
        },
        appwfapproval:{
          commit:'Commit',
          wait:'Waiting',
          handle:'Handle',
          placeholder:'Please enter the content',
          end:'End'
        },
        contextmenudrag:{
          allapp:'All applications',
          nofind:'The app was not found',
          error: "Error loading data",
          portlet: "enterprise portal"
        },
        filtermode:{
          placeholder:'Conditional logic',  
        },
        filtertree:{
          title1:'Add condition',
          title2:'Add group',
          placeholder:'Attribute', 
        },
        ibizgrouppicker:{
          ok:'Ok',
          cancel:'Cancel',
        },
        ibizgroupselect:{
          groupselect:'Group selection'
        },
        tabpageexp:{
          more:'More',
        },
        uploadfile:{
          imgmsg:'Drag the picture here to replace it',
          localupload:'Local upload',
          or:'Or',
          imgmsg1:'Select from stock',
          choose: "Select Upload File"
        },
        lockscren:{
          title:'Set the password lock screen',
          label:'password',
          message:'The lock screen password cannot be empty',
          placeholder:'Please enter the lock screen password',
          placeholder1:'Please enter your login password',
          message1: 'The unlock password is wrong. Please reenter it',
          promptInformation:'Do you want to quit the system? Do you want to continue?',
          prompt:'prompt',
          confirmbuttontext:'determine',
          cancelbuttontext:'cancel',
          lock: "Application of lock screen"
        },
        croneditor: {
          label: {
            second: 'Second',
            minute: 'Minute',
            hour: 'Hour',
            day: 'Day',
            week: 'Week',
            month: 'Month',
            year: 'Year',
          },
          public : {
            specify: 'Specify',
            notspecify: 'Not specify',
            cycle: 'Cycle',
            from: 'From',
            to: 'to',
            loop: 'Loop',
            every: 'Every',
            once: 'execute once',
          },
          second: 'second',
          minute: 'minute',
          hour: {
            title: 'hour',
            everyhour: 'Every hour',
            hourstart: "o'clock start, every ",
            onceahour: "o'clock to execute once.",
          },
          day: {
            title: 'day',
            daily: 'Every day',
            workday: 'Working day',
            daystart: 'rd start, every ',
            onceaday: 'rd to execute once',
            thismonth: 'this month',
            lastworkday: 'rd, Recent working day',
            lastdayofmonth: 'Last day of the month',
          },
          month: {
            title: 'month',
            everymonth: 'Every month',
            monthstart: ' month start, every',
            onceamonth: ' month to execute once',
          },
          week: {
            title: 'week',
            everyweek: 'Every week',
            from: '',
            to: 'from weekday',
            start: 'start, every',
            onceaday: 'days to execute once',
            specifyweek: 'Specify week',
            weekofmonth: 'this month',
            weekweek: 'week, weekday',
            lastofmonth: 'Last of the month',
            week: 'weekday',
          },
          year: {
            title: 'year',
            everyyear: 'Every year',
          },
          message: {
            error1: 'Date and day of the week cannot be "not specified" at the same time',
            error2: 'Both the date and the day of the week must be "not specified"',
          },
        },
        appmessagepopover: {
          loadmore: 'Load more',
          nomore: 'No more',
          error: "Error loading data",
          geterror: "The tag content was not retrieved",
          errorreturn: "Event trigger source has no value, force return"
        },
        diskFileUpload:{
          fileDrag: 'Drag the file here, or',
          clickUpload: 'Click on the upload',
          load: 'Load',
          preview: 'preview',
          edit: 'Edit',
          delete: 'Delete',
          clues: 'Individual file sizes do not exceed',
          clues1: 'Documents not exceeding',
          getFileFailure: 'Failed to get file list',
          loadFailure: 'Upload file failed',
          loadFailure1: 'Upload failed, individual file cannot exceed',
          downloadFile: 'Download file failed',
          downloadFile1: 'Download file failed, not get file!',
          deleteFile: 'This action will permanently delete the file. Do you want to continue?',
          deleteFilePrompt: 'Prompt',
          true: 'True',
          false: 'False',
          deleteFileFailure: 'File deletion failed',
          updateFailure: 'Batch update file failed',
        },
        diskimageupload:{
          preview: 'Preview',
          ocrdiscern: 'OCRdiscern',
          load: 'Load',
          delete: 'Delete',
          getimagefailure: 'Failed to get list of images',
          loadimagefailure: 'Failed to download thumbnails',
          loadimagefailure1: 'Failed to download thumbnail, failed to get file!',
          loadimagefailure2:'Download image failed',
          loadimagefailure3:'Download picture failed, no picture found!',
          imageidnone: 'Picture ID does not exist!',
          uploadimagefailure: 'Failed to upload image',
          uploadimagefailure1: "Upload failed, only support 'GIF, JPG, PNG, BMP' format images!",
          uploadfailure: 'Upload failed, individual image must not exceed',
          notimageurl: 'The image URL does not exist',
          deletefile: 'This action will permanently delete the file. Do you want to continue?',
          deletefileprompt: 'Prompt',
          true: 'True',
          false: 'False',
          deleteimagefailure:'Image deletion failed',
          updatefailure: 'Batch update file failed',
        },
        appmapposition: {
          submit: 'Submit',
          title: 'Please select address',
          address: "Tianfu Panda Pagoda, Sichuan TV Tower, Mengchuanwan Street, Chenghua District, Chengdu City, Sichuan Province",
          city: "Chengdu"
        },
        appsortbar: {
          title: 'Sort'
        },
        appaftertime:{
          minutesago: 'minutes ago',
          hoursago: 'hours ago',
          dayago: 'days ago',
          monthsago: 'months ago',
          yearsago: 'years ago'
        },
        timeline: {
          index: "serial",
          node: "node",
          author: "accepting officer",
          type: "operation",
          lasttime: "completion time",
          opinion: "approval opinion",
          inhand: "inhand:",
        },
        content: {
          title: "「Open/Close」Bottom Page [Ctrl + ']",
          open: "On the menu",
          close: "Pack up the menu"
        },
        richtext: {
          content: "Please enter the contents of the template",
          title: "Please enter a template title",
          save: "Save the template",
          pub: "public",
          pri: "private",
          apply: "application template",
          success: "Save template successfully!!!",
          error: "Failed to save template!!!",
          delsuccess: "Delete template successfully!!!",
          delerror: "Failed to delete template!!!"
        },
        search: {
          holder: "Search content"
        },
        drawer: {
          back: "back",
          confirm: "Make sure to close all interfaces?",
          close: "Close all views",
          toperror: "Upper-bay window opens view parameter conversion exception"
        }
    };
    return data;
  }
  export default getLocaleResourceBase;