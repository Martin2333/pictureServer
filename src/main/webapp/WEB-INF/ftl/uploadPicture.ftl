<!DOCTYPE html>
<html>
<head>
    <title>文件上传实例-test</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link type="text/css" rel="stylesheet" href="/statics/css/uploadPicture.css" />
</head>
<body>
<h1>文件上传实例-test</h1>
<form method="post" action="/uploadPicture/uploadPicture" enctype="multipart/form-data">
    <table>
        <tbody>
        <th>品牌：</th>
        <td>
            <select class="select_params" name="brand_no">
                <option value="NK">NK</option>
                <option value="AD">AD</option>
            </select>
        </td>
        <th>图片类型：</th>
        <td>
            <select class="select_params" name="brand_no">
                <option value="product">商品</option>
                <option value="store">店铺</option>
            </select>
        </td>
        <th>图片来源：</th>
        <td>
            <select class="select_params" name="brand_no">
                <option value="MDM">主数据</option>
                <option value="ORM">订采系统</option>
            </select>
        </td>
        </tbody>
    </table>

    <div>
        选择一个文件:
    </div>
    <input id="file-input" name="file-input" type="file" accept="image/*" multiple/>
    <br/><br/>
    <input type="submit" value="上传" />
</form>
</body>
</html>