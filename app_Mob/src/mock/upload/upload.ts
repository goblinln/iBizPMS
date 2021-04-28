import Mock from 'mockjs'
const Random = Mock.Random;
import { MockAdapter } from '../mock-adapter';
const mock = MockAdapter.getInstance();
mock.onPost(new RegExp(/^\.\.\/ibizutil\/upload$/)).reply((config: any) => {
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    let convert_FormData_to_json = function (formData: any) {
        let objData: any = {};
        formData.forEach((value: any, key: any) => objData[key] = value);
        return {
            name: objData["file"]["name"],
            id: Random.guid()
        };
    };
    let data = convert_FormData_to_json(config.data);

    return [status, data, config.headers, config];
});