import File_en_US_Base from './file_en_US_base';

function getLocaleResource(){
    const File_en_US_OwnData = {};
    const targetData = Object.assign(File_en_US_Base(), File_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;