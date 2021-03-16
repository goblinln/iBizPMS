import IbzReport_zh_CN_Base from './ibz-report_zh_CN_base';

function getLocaleResource(){
    const IbzReport_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzReport_zh_CN_Base(), IbzReport_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;