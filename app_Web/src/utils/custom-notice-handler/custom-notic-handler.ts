import { NoticeHandler } from "ibiz-vue";

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
    NoticeHandler.hooks.beforeSuccess.tap(handleCommentTooLong)
}