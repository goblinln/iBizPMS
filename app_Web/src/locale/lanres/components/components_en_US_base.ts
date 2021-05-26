function getLocaleResourceBase(){
    const data:any = {
        "404": {
            errorText1: 'sorry，the page you visited is not exist！',
            errorText2: 'The page you visited is not exist，please return to',
            indexPage: 'home page',
            continue: 'to continue browsing',
        },
        "500": {
            errorText1: "sorry，there's something wrong with the server！",
            errorText2: "There's something wrong with the server，please return to",
            indexPage: 'home page',
            continue: 'to continue browsing',
        },
        appOrgSector:{
          successSwitch:'The switch was successful',
          errorSwitch:'The switchover failed',
        },
        appAutocomplete: {
            error: 'Error',
            miss: 'Missing parameter ',
            requestException: 'Request Exception！',
        },
        appBuild: {
            custom: 'Customize',
        },
        appCheckBox: {
            notExist: 'codelist is not existed',
            warn: 'Code table value type and property type do not match, automatic cast exception, please correct code table value type and property type match'
        },
        appColumnLink: {
          error: 'error',
          valueItemException:"value item exception",
          rowDataException:"table row data exception",
        },
        appColumnRender: {
          select: 'please select...',
          unsupported: 'unsupported',
        },
        microcom: {
          filterWarn: "Filter parameter configuration error, please check!"
        },
        appDashboardDesign: {
          global: 'Global',
        },
        appDataUploadView: {
          "selectfile":"Click this area to upload",
          "uploadserver":"Upload Server",
          "datatemplate":"Import Data Template",
          "dataTemplateMessage":"Download the Import Data Template, According to the specification required to fill in",
          "read":" read Successful",
          "completed":"Import Completed",
          "confirm":"OK",
          "cancel":"Cancel",
          "importfailed":"Import Failed"
        },
        appDebugActions: {
          button: 'Open Configuration Mode',
        },
        appExportExcel: {
          total: 'All',
          max: 'At Most',
          row: 'Lines',
          currentPage: 'Current Page',
          desc:'Please enter the start page',
          desc1:'Please enter a valid start page',
        },
        appFileUpload: {
          preview: 'preview',
          uploadText: 'Drag files here，or <em>Click</em> to upload',
          fileTypeErrorTitle: 'File type incorrect',
          fileTypeErrorInfo: 'Please select files with picture types，such as JPEG，GIF，PNG，BMP',
          downloadError: "Image download failed!"
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
        appFormDRUIPart: {
          blockUITipInfo: 'Please save the major data first',
          viewLoadComp:'After the multi data view is loaded, the subsequent form item update will be triggered',
          save:'Relationship data save complete',
          change:'Relationship data value change',
          change1:'View data changes',
          loadComp:'View loading complete',
        },
        appHeaderMenus: {
          ibizlab:{
              title: 'iBizLab',
          },
          publishProject:{
              title: 'Publish Project',
          },
          ibizstudio:{
              title: 'Model Design Tools',
          },
          ibizbbs:{
              title: 'iBizBBS',
          },
        },
        appMpicker: {
          error: 'Error',
          miss: 'Missing parameter ',
          requestException: 'Request Exception！',
        },
        appPicker: {
          error: 'Error',
          miss: 'Missing parameter ',
          requestException: 'Request Exception！',
          newAndEdit: 'Create And Edit...',
          systemException: 'System Error！',
          valueitemException: 'valueitem Error！',
          formdataException: 'formdata Error！',
          nosupport: "The redirect view is not yet supported"
        },
        appPickerSelectView: {
          error: 'Error',
          valueitemException: 'valueitem Error！',
          formdataException: 'formdata Error！',
          placeholder: 'Please select...',
        },
        appPortalDesign: {
          customPortal: 'Custom portal',
          recover: "restore default",
          save: 'Save',
        },
        appRangDate: {
          placeholder: 'Please select time...',
          from: 'from',
          daystart: '00:00:00 to',
          dayend: '24:00:00',
        },
        appRangeEditor: {
          placeholder: 'Please select time...',
          input: 'Please input...',
        },
        appStudioAction: {
          configTitle: 'Enter the configuration of current view',
          configButton: 'Configuration',
          issueTitle: 'Create issues of current view',
          issueButton: 'Create issues',
          success: "Copy success!",
          copyname: "Click copy view name",
          view: "check"
        },
        appTreePicker: {
          placeholder: 'please select...',
        },
        dateRange: {
          startText: 'Insurance period ： From',
          endText: '24:00:00',
          startPlaceholder: 'Begin Date',
          rangeSeparatorr: ' 00:00:00 To',
          endPlaceholder: 'End Dat4e',
        },
        dropDownList: {
          placeholder: 'please select...',
          valueError: "Drop down list, value conversion failed",
        },
        dropDownListDynamic: {
          placeholder: 'please select...',
        },
        dropDownListMpicker: {
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
        appUser: {
          name: 'System',
          logout: 'Logout',
          surelogout: 'Are you sure logout?',
          changepwd: "Change Password",
        },
        appTheme: {
          caption: {
            theme: 'Theme',
            font: 'Font family',
          },
          fontFamilys: {
              MicrosoftYaHei: 'Microsoft YaHei',
              SimHei: 'SimHei',
              YouYuan: 'YouYuan',
          },
          config: "The topic configuration",
          customTheme: "Custom Theme",
          color: "subject color",
          preview: "preview",
          save: "Save the configuration",
          reset: "reset",
          success: "Save custom theme successfully"
        },
        appFormGroup: {
          hide: 'hide',
          showMore: 'show more',
        },
        appUpdatePassword: {
          oldPwd: 'Original password',
          newPwd: 'New password',
          confirmPwd: 'Confirm password',
          sure: 'Confirm modification',
          oldPwdErr: 'The original password cannot be empty!',
          newPwdErr: 'New password cannot be empty!',
          confirmPwdErr: 'The two input passwords are inconsistent!',
        },
        appAddressSelection: {
          loadDataFail: 'City data loading failed'
        },
        appGroupSelect:{
          groupSelect:'Group selection', 
        },
        appImageUpload:{
          uploadFail:'Upload failed'
        },
        appOrgSelect:{
          loadFail:'Failed to load data',
          resetError: "An exception occurred in resetting application data"
        },
        appTransfer:{
          title1:'Not selected',
          title2:'Selected',
        },
        appWFApproval:{
          commit:'Commit',
          wait:'Waiting',
          handle:'Handle',
          placeholder:'Please enter the content',
          end:'End'
        },
        contextMenuDrag:{
          allApp:'All applications',
          noFind:'The app was not found',
          error: "Error loading data",
          portlet: "enterprise portal"
        },
        filterMode:{
          placeholder:'Conditional logic',  
        },
        filterTree:{
          title1:'Add condition',
          title2:'Add group',
          placeholder:'Attribute', 
        },
        iBizGroupPicker:{
          ok:'Ok',
          cancel:'Cancel',
        },
        iBizGroupSelect:{
          groupSelect:'Group selection'
        },
        tabPageExp:{
          more:'More',
        },
        uploadFile:{
          imgMsg:'Drag the picture here to replace it',
          localUpload:'Local upload',
          or:'Or',
          imgMsg1:'Select from stock',
          choose: "Select Upload File"
        },
        lockScren:{
          title:'Set the password lock screen',
          label:'password',
          message:'The lock screen password cannot be empty',
          placeholder:'Please enter the lock screen password',
          placeholder1:'Please enter your login password',
          message1: 'The unlock password is wrong. Please reenter it',
          promptInformation:'Do you want to quit the system? Do you want to continue?',
          prompt:'prompt',
          confirmButtonText:'determine',
          cancelButtonText:'cancel',
          lock: "Application of lock screen"
        },
        cronEditor: {
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
            notSpecify: 'Not specify',
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
            everyHour: 'Every hour',
            hourStart: "o'clock start, every ",
            onceAHour: "o'clock to execute once.",
          },
          day: {
            title: 'day',
            daily: 'Every day',
            workDay: 'Working day',
            dayStart: 'rd start, every ',
            onceADay: 'rd to execute once',
            thisMonth: 'this month',
            lastWorkDay: 'rd, Recent working day',
            lastDayOfMonth: 'Last day of the month',
          },
          month: {
            title: 'month',
            everyMonth: 'Every month',
            monthStart: ' month start, every',
            onceAMonth: ' month to execute once',
          },
          week: {
            title: 'week',
            everyWeek: 'Every week',
            from: '',
            to: 'from weekday',
            start: 'start, every',
            onceADay: 'days to execute once',
            specifyWeek: 'Specify week',
            weekOfMonth: 'this month',
            weekWeek: 'week, weekday',
            lastOfMonth: 'Last of the month',
            week: 'weekday',
          },
          year: {
            title: 'year',
            everyYear: 'Every year',
          },
          message: {
            error1: 'Date and day of the week cannot be "not specified" at the same time',
            error2: 'Both the date and the day of the week must be "not specified"',
          },
        },
        appMessagePopover: {
          loadMore: 'Load more',
          noMore: 'No more',
          error: "Error loading data",
          getError: "The tag content was not retrieved",
          errorReturn: "Event trigger source has no value, force return"
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
        diskImageUpload:{
          preview: 'Preview',
          OCRdiscern: 'OCRdiscern',
          load: 'Load',
          delete: 'Delete',
          getImageFailure: 'Failed to get list of images',
          loadImageFailure: 'Failed to download thumbnails',
          loadImageFailure1: 'Failed to download thumbnail, failed to get file!',
          loadImageFailure2:'Download image failed',
          loadImageFailure3:'Download picture failed, no picture found!',
          ImageIdNone: 'Picture ID does not exist!',
          uploadImageFailure: 'Failed to upload image',
          uploadImageFailure1: "Upload failed, only support 'GIF, JPG, PNG, BMP' format images!",
          uploadFailure: 'Upload failed, individual image must not exceed',
          notImageUrl: 'The image URL does not exist',
          deleteFile: 'This action will permanently delete the file. Do you want to continue?',
          deleteFilePrompt: 'Prompt',
          true: 'True',
          false: 'False',
          deleteImageFailure:'Image deletion failed',
          updateFailure: 'Batch update file failed',
        },
        appMapPosition: {
          submit: 'Submit',
          title: 'Please select address',
          address: "Tianfu Panda Pagoda, Sichuan TV Tower, Mengchuanwan Street, Chenghua District, Chengdu City, Sichuan Province",
          city: "Chengdu"
        },
        appSortBar: {
          title: 'Sort'
        },
        appAfterTime:{
          minutesAgo: 'minutes ago',
          hoursAgo: 'hours ago',
          dayAgo: 'days ago',
          monthsAgo: 'months ago',
          yearsAgo: 'years ago'
        },
        timeline: {
          index: "serial",
          node: "node",
          author: "accepting officer",
          type: "operation",
          lastTime: "completion time",
          opinion: "approval opinion"
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
          delSuccess: "Delete template successfully!!!",
          delError: "Failed to delete template!!!"
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