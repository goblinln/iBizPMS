import IbzReportly_zh_CN_Base from './ibz-reportly_zh_CN_base';

function getLocaleResource(){
    const IbzReportly_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzReportly_zh_CN_Base(), IbzReportly_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;