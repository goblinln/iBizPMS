import { NoticeHandler } from "ibiz-vue";

/**
 * 用户没有权限时禁用所有表单成员
 *
 * @param {{ error: any, param: any }} { error, param }
 * @memberof FormControlBase
 */
function handleAuth({ error, param, caller, fnName }: { error: any, param: any, caller: any, fnName: string }){
    if(caller?.controlInstance?.controlType == 'FORM' && fnName == 'load'){
        if(error?.status == 403){
            // 禁用异常提示
            error.ignore = true;
            // 用户没权限禁用所有的表单成员
            Object.keys(caller.detailsModel).forEach((key: string)=>{
                caller.detailsModel[key].disabled = true;
            })
            caller.$forceUpdate();
        }
    }
}

/**
 * 处理备注保存成功提示信息过长。
 *
 * @param {{ message: any, param: any }} { error, param }
 * @memberof FormControlBase
 */
function handleCommentTooLong(args: { message: any, param: any, caller: any, fnName: string }){
    const { caller, fnName } = args;
    if(caller?.controlInstance?.controlType == 'FORM' && caller.appDeCodeName == 'Action' && fnName == 'save'){
        args.message = '评论成功';
    }
}

export function initNoticeHandler(){
    NoticeHandler.hooks.beforeError.tap(handleAuth)
    NoticeHandler.hooks.beforeSuccess.tap(handleCommentTooLong)
}