package integrationend.Utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String code;
    private String msg;
    private Object result;

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Response buildSuccess(Object result) {
        return new Response("00000", "Success", result);
    }

    public static Response buildSuccess() {
        return new Response("00000", "Success", "操作成功");
    }
    public static Response buildSuccess(String code, String msg) {
        return new Response(code, msg, null);
    }



    public static Response buildFailure() {
        return new Response("11111", "Failure", "操作失败");
    }

    public static Response buildFailed(String code, RuntimeException e) {
        return new Response(code, e.getLocalizedMessage(), null);
    }

    public static Response buildFailed(String code, String msg) {
        return new Response(code, msg, null);
    }
}
