import IbzProTestTaskAction_zh_CN_Base from './ibz-pro-test-task-action_zh_CN_base';

function getLocaleResource(){
    const IbzProTestTaskAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzProTestTaskAction_zh_CN_Base(), IbzProTestTaskAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;