package SOA;

import java.util.*;

public class RandomQ {

    private static List<String> SOA_QUESTION = new ArrayList<>(Arrays.asList(
            "1.\tSo sánh các phương pháp giao tiếp giữa các tiến trình socket và RPC\n",
            "2.\tVai trò của IDL trong RPC là gì?\n" ,
            "3.\tVai trò của danh bạ (registry) trong Java RMI là gì?\n" ,
            "4.\tCho ví dụ về một phần tử với một thuộc tính trong XML\n" ,
            "5.\tSo sánh DTD và XML Schema\n" ,
            "6.\tCho một ví dụ về định dạng dữ liệu theo JSON\n" ,
            "7.\tSo sánh JSON và XML\n" ,
            "8.\tCho biết vai trò của SOAP, WSDL trong dịch vụ Web\n" ,
            "9.\tThông thường, dịch vụ Web sẽ sử dụng HTTP. Hãy hình dung cách dịch vụ Web chạy trên SMTP.\n" ,
            "10.\tTrình bày tổng quan về REST\n" ,
            "11.\tPhân biết đồng bộ và bất đồng bộ. REST APIs là đồng bộ hay bất đồng bộ\n" ,
            "12.\tTrình bày tổng quan về gRPC\n" ,
            "13.\tChọn một trong hai chủ đề API Gateway/Micro frontend và trình bày\n" ,
            "14.\tCho biết quan điểm về mối quan hệ giữa SOA và Microservices\n" ,
            "15.\tTìm hiểu một số patterns sử dụng trong việc chuyển hệ thống từ đơn khối sang vi dịch vụ (code và data)\n" ,
            "16.\tTrình bày hai cách khám phá dịch vụ (client side và server side)\n" ,
            "17.\tNêu một số nguy cơ về an ninh đối với API\n" ,
            "18.\tNêu một số giải pháp để đảm bảo an ninh cho API\n" ,
            "19.\tESB là gì? Một số chức năng của ESB\n" ,
            "20.\tBPMN là gì? Nêu một số sản phẩm khác có thể thay thế cho Camunda\n"));

    private static List<String> SA_QUESTION = new ArrayList<>(Arrays.asList(
            "1)\tTại sao kiến trúc phần mềm rất quan trọng?\n",
            "2)\tMột số các thuộc tính chất lượng và mối quan hệ giữa thuộc tính chất lượng với kiến trúc phần mềm \n" ,
            "3)\tCác nguyên lý thiết kế SOLID, DRY, KISS, YAGNI\n" ,
            "4)\tNắm được các mẫu thiết kế: Factory method, Abstract factory, Decorator\n" ,
            "5)\tMột số kỹ thuật tái cấu trúc (ít nhất 12 kỹ thuật đã làm trong bài tập lớn)\n" ,
            "6)\tCác bước chính trong thiết kế kiến trúc theo phương pháp ADD và phương pháp của Microsoft\n" ,
            "7)\tMột số kiểu kiến trúc (theo Microsoft, nắm được ít nhất 4 kiểu)\n" ,
            "8)\tNắm được tổng quan 10 nguyên lý thiết kế cho ứng dụng (trên Azure)\n" ,
            "9)\t Khái niệm về vi dịch vụ, ưu và nhược điểm\n" ,
            "10)\t Một số vấn đề cần quan tâm khi thiết kế ứng dụng theo kiến trúc vi dịch vụ\n"
    ));


    public static void main(String[] args) {

        while (true) {
            int index = Math.abs(new Random().nextInt())%20;
            Scanner sc = new Scanner(System.in);
            String request = sc.next();
            if(request.equals("soa")){
                System.out.println(SOA_QUESTION.get(index));
            }
            if(request.equals("sa")){
                System.out.println(SA_QUESTION.get(index));
            }
        }
    }
}
