<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-2.1.4.js" charset="UTF-8"></script>
</head>
<body>
    省份：
    <select id="provinceId" onchange="selectCity(this)">
        <option>--------请选择--------</option>
    </select>
    城市：
    <select id="cityId" onchange="selectArea(this)">
        <option>--------请选择--------</option>
    </select>
    区县：
    <select id="areaId">
        <option>--------请选择--------</option>
    </select>

    <script type="text/javascript">
        $(function () {
            $.post(
                '<%=request.getContextPath()%>/area?method=selectProvince',
                function(jsonObj) {
                    console.log(jsonObj);
                    $(jsonObj).each(function () {
                        // <option value="001">山东省</option>
                        $('#provinceId').append('<option value="' + this.id + '">' + this.province + '</option>')
                    });
                },
                'json'
            );
        });

        function selectCity(obj) {
            console.log(obj);
            var provinceId = $(obj).val();
            $.post(
                '<%=request.getContextPath()%>/area?method=selectCity',
                {'provinceId' : provinceId},
                function(jsonObj) {
                    console.log(jsonObj);

                    $('#cityId option:gt(0)').remove();
                    $(jsonObj).each(function () {
                        $('#cityId').append('<option value="' + this.id + '">' + this.city + '</option>')
                    });
                },
                'json'
            );
        }

        function selectArea(obj) {
            console.log(obj);
            var cityId = $(obj).val();
            $.post(
                '<%=request.getContextPath()%>/area?method=selectArea',
                {'cityId' : cityId},
                function(jsonObj) {
                    console.log(jsonObj);

                    $('#areaId option:gt(0)').remove();
                    $(jsonObj).each(function () {
                        $('#areaId').append('<option value="' + this.id + '">' + this.area + '</option>')
                    });
                },
                'json'
            );
        }
    </script>
</body>
</html>
