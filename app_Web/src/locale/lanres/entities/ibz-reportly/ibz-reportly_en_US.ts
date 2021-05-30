import IbzReportly_en_US_Base from './ibz-reportly_en_US_base';

function getLocaleResource(){
    const IbzReportly_en_US_OwnData = {};
    const targetData = Object.assign(IbzReportly_en_US_Base(), IbzReportly_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;