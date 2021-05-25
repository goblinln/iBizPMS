import IbzProTestTaskAction_en_US_Base from './ibz-pro-test-task-action_en_US_base';

function getLocaleResource(){
    const IbzProTestTaskAction_en_US_OwnData = {};
    const targetData = Object.assign(IbzProTestTaskAction_en_US_Base(), IbzProTestTaskAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;