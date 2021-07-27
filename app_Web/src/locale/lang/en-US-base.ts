import components_en_US from '@locale/lanres/components/components_en_US';
import usercustom_en_US from '@locale/lanres/usercustom/usercustom_en_US';

function getAppLocale(){
    const data:any = {
             app: {
            commonwords:{
                error: "Error",
                success: "Success",
                ok: "OK",
                cancel: "Cancel",
                save: "Save",
                codenotexist: 'Code list does not exist',
                reqexception: "Request exception",
                sysexception: "System abnormality",
                warning: "Warning",
                wrong: "Error",
                rulesexception: "Abnormal value check rule",
                savesuccess: "Saved successfully",
                savefailed: "Save failed",
                deletesuccess: "Successfully deleted!",
                deleteerror: "Failed to delete",
                deldatafail: "Failed to delete data",
                nodata: "No data",
                startsuccess:"Start successful",
                loadmore:"Load more",
                nomore:"No more",
                other:"other",
                nosupportsingle: "Single item data is not supported",
                nosupportmultile: "Multiple data is not supported",
                getappiderror: "Failed to get web application appid",
                getdinginfoerror: "Failed to retrieve user information",
                getiderror: "Failed to get the enterprise ID",
                noassign: "No application functionality specified",
                codelistwarn: "Code table value type and property type do not match, automatic cast exception, please correct code table value type and property type match",
                yes: "yes",
                no: "no",
                wrongdataformat:"The data format is wrong!",
                isexist:"Exist",
                srfmajortext: "Major Text"
            },
            local:{
                new: "New",
                add: "Add",
            },
            grid: {
                choicecolumns: "Choice columns",
                refresh: "refresh",
                show: "Show",
                records: "records",
                totle: "totle",
                valuevail: "Value cannot be empty",
                group:"Group",
                other:"Other",
                notconfig: {
                    fetchaction: "The view table fetchaction parameter is not configured",
                    removeaction: "The view table removeaction parameter is not configured",
                    createaction: "The view table createaction parameter is not configured",
                    updateaction: "The view table updateaction parameter is not configured",
                    loaddraftaction: "The view table loadtrafaction parameter is not configured",
                },
                data: "Data",
                deldatafail: "Failed to delete data",
                delsuccess: "Delete successfully!",
                confirmdel: "Are you sure you want to delete",
                notrecoverable: "delete will not be recoverable?",
                notbatch: "Batch addition not implemented",
                grid: "Grid",
                exportfail: "Data export failed",
                sum: "Total",
                formitemfailed: "Form item update failed",
                dataaggregate:{
                    dataaggregate: "Aggregate",
                    sum: "Sum：",
                    avg: "Avg：",
                    max: "Max：",
                    min: "Min：",
                },
            },
            list: {
                notconfig: {
                    fetchaction: "View list fetchAction parameter is not configured",
                    removeaction: "View table removeAction parameter is not configured",
                    createaction: "View list createAction parameter is not configured",
                    updateaction: "View list updateAction parameter is not configured",
                },
                confirmdel: "Are you sure you want to delete",
                notrecoverable: "delete will not be recoverable?",
            },
            listexpbar: {
                title: "List navigation bar",
            },
            wfexpbar: {
                title: "Process navigation bar",
            },
            calendarexpbar:{
                title: "Calendar navigation bar",
            },
            treeexpbar: {
                title: "Tree view navigation bar",
            },
            portlet: {
                noextensions: "No extensions",
            },
            tabpage: {
                sureclosetip: {
                    title: "Close warning",
                    content: "Form data Changed, are sure close?",
                },
                closeall: "Close all",
                closeother: "Close other",
            },
            searchbutton: {
                search: "Search",
                reset: "Reset",
            },
            calendar:{
            today: "today",
            month: "month",
            week: "week",
            day: "day",
            list: "list",
            dateselectmodaltitle: "select the time you wanted",
            gotodate: "goto",
            from: "From",
            to: "To",
            },
            utilview:{
                importview:"Import Data",
                warning:"warning",
                info:"Please configure the data import item"    
            },
            formpage:{
                error: "Error",
                desc1: "Operation failed, failed to find current form item",
                desc2: "Can't continue",
                notconfig: {
                    loadaction: "View form loadAction parameter is not configured",
                    loaddraftaction: "View form loaddraftAction parameter is not configured",
                    actionname: "View form actionName parameter is not configured",
                    removeaction: "View form removeAction parameter is not configured",
                },
                saveerror: "Error saving data",
                savecontent: "The data is inconsistent. The background data may have been modified. Do you want to reload the data?",
                valuecheckex: "Value rule check exception",
                savesuccess: "Saved successfully!",
                deletesuccess: "Successfully deleted!",  
                workflow: {
                    starterror: "Workflow started successfully",
                    startsuccess: "Workflow failed to start",
                    submiterror: "Workflow submission failed",
                    submitsuccess: "Workflow submitted successfully",
                },
                updateerror: "Form item update failed",  
                valueverif:"Please input ",
                updateAction: "Update", 
                createAction: "Create",
                compositeitem: {
                    datepicker: 'The end time cannot be less than the start time',
                },     
            },
            gridexpbar: {
                title: "Table navigation bar",
            },
            multieditview: {
                notconfig: {
                    fetchaction: "View multi-edit view panel fetchAction parameter is not configured",
                    loaddraftaction: "View multi-edit view panel loaddraftAction parameter is not configured",
                },
            },
            dataviewexpbar: {
                title: "Card view navigation bar",
            },
            kanban: {
                notconfig: {
                    fetchaction: "View list fetchAction parameter is not configured",
                    removeaction: "View table removeAction parameter is not configured",
                },
                delete1: "Confirm to delete ",
                delete2: "the delete operation will be unrecoverable!",
            },
            dashboard: {
                handleclick: {
                    title: "Panel design",
                },
                dataerror: "Data Exception",
                serviceerror: "Server exception",
                loaderror: "Error loading panel model"
            },
            dataview: {
                sum: "total",
                data: "data",
                useless: "Packet data invalid"
            },
            chart: {
                undefined: "Undefined",
                quarter: "Quarter",   
                year: "Year",
                noindicator: "Indicator does not exist. Cannot convert data set!"
            },
            searchform: {
                notconfig: {
                    loadaction: "View search form loadAction parameter is not configured",
                    loaddraftaction: "View search form loaddraftAction parameter is not configured",
                },
                custom: "Store custom queries",
                title: "Name",
                globalerrortip:"Please check if the information is incorrect",
            },
            wizardpanel: {
                back: "Back",
                next: "Next",
                complete: "Complete",
                preactionmessage:"The calculation of the previous behavior is not configured",
                nofind: "Process function action view not found",
                success: "Submitted data successfully",
                error: "Mark the backlog task as read failed"
            },
            viewlayoutpanel: {
                applogoutview: {
                    prompt1: "Dear customer, you have successfully exited the system, after",
                    prompt2: "seconds, we will jump to the",
                    logingpage: "login page",
                },
                appwfsteptraceview: {
                    title: "Application process processing record view",
                },
                appwfstepdataview: {
                    title: "Application process tracking view",
                },
                apploginview: {
                    username: "Username",
                    password: "Password",
                    login: "Login",
                },
            },
            viewpanel: {
                noconfig: {
                    getaction: "The panel gets data behavior is not configured"
                },
                error: {
                    notgetservice: "Can't get entity service"
                }
            },
            editor: {
              noexist: "Editor instance does not exist!",
              nooutput: "The base class does not output",
              unsupport: "The editor type is not currently supported",
              nofind: "The destination editor could not be found",
              error: "Mark the cc task as read failed"
            },
            button: {
              leftbtn: "left shift",
              rightbtn: "right shift",
              allleftbtn: "All left",
              allrightbtn: "All the moves to the right",
            },
            nosupport: {
              nosupport: "This component is not currently supported",
              layout: "This layout pattern is not currently supported",
              unopen: "This mode is not supported to open",
              unassign: "The relational view is not specified",
              uncustom: "Custom not implemented"
            },
            warn: {
              nton: "Batch add need to add N:N relation",
              batcherror: "The batch operation failed" ,
              unbatchadd: "Only batch additions are supported unimplemented",
              geterror: "Data acquisition exception",
              load: "Loading data",
              nofind: "No relevant data could be found",
              unopendata: "There is no opendata",
              unnewdata: "There is no newdata",
              unremove: "Don't remove",
              unrefresh: "There is no refresh",
              unopenview: "No open view"
            },
            error: {
                ok: " Success, no mistakes",
                internalerror: " Internal error occurred",
                accessdeny: " Access denied",
                invaliddata: " Invalid data",
                invaliddatakeys: " Invalid data key",
                inputerror: " The input is incorrect",
                duplicatekey: " Duplicate data key values",
                duplicatedata: " Duplicate data",
                daletereject: " Remove restrictions",
                logicerror: " Logic handling error",
                datanotmatch: " Data mismatch",
                daletedata: " Data that has been deleted",
                userconfirm: " Need to be validated",
                notimpl: " The specified function was not implemented",
                modelerror: " The model error",
                usererror: " User error",
                systemerror: "System anomaly",
                error_ok: "Success, no mistakes",
                error_empty: "Error with null data entry",
                error_datatype: "Incorrect data type error",
                error_valuerule: "Value rule error",
                error_duplicate: "Value duplicate error",
            },
        },
        components: components_en_US(),
        usercustom: usercustom_en_US(),
    };
    return data;
}
export default getAppLocale;