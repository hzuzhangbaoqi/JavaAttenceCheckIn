package com.attencecheckin.javabackend;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: javabackend
 * @description:
 * @author: zxf
 * @create: 2019-05-04 05:14
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.yml"})
@SpringBootTest
@MapperScan("com.*.dao")
@MapperScan("com.*.repo")
public class test {
    /*@Autowired
    private PostsRepos postsRepos;
    @Autowired
    private FilmDescRepos filmDescRepos;*/


    private static CloseableHttpClient client =HttpClients.createDefault();

//    @Test
//    public void getPostscontent() throws IOException {
//        String Url = "";
//        Map<String, String> header = new HashMap<String, String>();
//        header.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; InfoPath.1; SV1; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E; .NET CLR 1.1.4322)");
//        header.put("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*");
//        header.put("Accept-Encoding", "gzip, deflate");
//        header.put("Accept-Language", "zh-CN");
//        header.put("Connection", "keep-alive");
//        header.put("Host", "s1.xp3839.rocks");
//        Document document = Jsoup.connect(Url).headers(header).get();
//        Elements tpc_content = document.body().getElementsByClass("tpc_content");
//        if(tpc_content.size()>0){
//            Pattern p2=Pattern.compile("【[\\u4e00-\\u9fa5]+】.*");
//            Matcher m2=p2.matcher(tpc_content.text().replaceAll("【","\n【" ));
//            List<FilmDesc> filmDescs = new ArrayList<FilmDesc>();
//            FilmDesc filmDesc = new FilmDesc();
//            while (m2.find()) {
//                System.out.println(m2.group());
//                if(m2.group().contains("影片名称")){
//                    filmDesc.setFilmName(m2.group().replaceAll("(?:【影片名称】:|【影片名称】：)", ""));
//                }else if(m2.group().contains("影片大小")){
//                    filmDesc.setFilmSize(m2.group().replaceAll("(?:【影片大小】:|【影片大小】：)", ""));
//                }else if(m2.group().contains("影片时间") || m2.group().contains("影片时长") ){
//                    filmDesc.setFilmDuration(m2.group().replaceAll("(?:【影片时间】:|【影片时间】：|【影片时长】:|【影片时长】：)", ""));
//                }else if(m2.group().contains("影片格式")){
//                    filmDesc.setFilmFormat(m2.group().replaceAll("(?:【影片格式】:|【影片格式】：)", ""));
//                }else if(m2.group().contains("是否有码")){
//                    filmDesc.setFilmCode(StringUtils.containsAny(m2.group().replaceAll("(?:【是否有码】:|【是否有码】：|是否有码)","" ),"无")?false:true);
//                }else if(m2.group().contains("影片背景") || m2.group().contains("视频背景")){
//                    filmDesc.setFilmBackground(m2.group().replaceAll("(?:【影片背景】:|【影片背景】：|【视频背景】:|【视频背景】：)", ""));
//                }else if(m2.group().contains("下载地址")){
//                    filmDescs.add(filmDesc);
//                    filmDesc = new FilmDesc();
//                }else{
//                    System.out.println(m2.group());
//                }
//            };
//
//            if(filmDescs!=null && filmDescs.size()>0){
//                int filmDescIndex=0;
//                Elements a = tpc_content.get(0).getElementsByTag("a");
//                List<ImgDesc> imgDescs = new ArrayList<ImgDesc>();
//                for (Element item:a){
//                    String url = item != null ? item.attr("href") : "";
//                    if(StringUtils.containsAny(item.text(),"点击进入","zip","下载")){
//                        filmDescs.get(filmDescIndex).setImages(imgDescs);
//                        filmDescs.get(filmDescIndex).setDownSrc(url);
//                        for (ImgDesc imgDesc :filmDescs.get(filmDescIndex).getImages()) {
//                            httpGetImg(client,imgDesc,filmDesc);
//                        }
//                        //downImgThread(filmDescs.get(filmDescIndex));
//                        filmDescRepos.saveAndFlush(filmDescs.get(filmDescIndex));
//                        filmDescIndex++;
//                        imgDescs = new ArrayList<ImgDesc>();;
//                    }else{
//                        //获取src
//                        Elements img = item.getElementsByTag("img");
//                        if(img.size()>0){
//                            url = item != null ? img.get(0).attr("src") : "";
//                            ImgDesc imgDesc = new ImgDesc(url);
//                            //imgDesc.setDownSrc(filmDesc);
//                            imgDescs.add(imgDesc);
//                        };
//                    }
//                }
//            }else{
//                Elements a = tpc_content.get(0).getElementsByTag("a");
//                List<ImgDesc> imgDescs = new ArrayList<ImgDesc>();
//                for (Element item:a){
//
//                    String url = item != null ? item.attr("href") : "";
//                    if(StringUtils.containsAny(item.text(),"点击进入","zip","下载")){
//
//                        filmDesc.setImages(imgDescs);
//                        filmDesc.setDownSrc(url);
//                        filmDescs.add(filmDesc);
//                        downImgThread(filmDesc);
//                        filmDescRepos.saveAndFlush(filmDesc);
//                        imgDescs = new ArrayList<ImgDesc>();
//                        filmDesc = new FilmDesc();
//                    }else{
//                        //获取src
//                        Elements img = item.getElementsByTag("img");
//                        if(img.size()>0){
//                            url = item != null ? img.get(0).attr("src") : "";
//                            ImgDesc imgDesc = new ImgDesc(url);
//                            //imgDesc.setDownSrc(filmDesc);
//                            imgDescs.add(imgDesc);
//                        };
//                    }
//                }
//            }
//
//            System.out.println(filmDescs);
//        }else{
//            System.out.println(String.format("%s not found ul body",Url));
//        }
//    }
//    public void downImgThread(FilmDesc filmDesc){
//        //线程池
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        CompletionService<HashMap<String, Object>> completionService = new ExecutorCompletionService<HashMap<String, Object>>(cachedThreadPool);
//
//        try{
//            for (ImgDesc item:filmDesc.getImages()) {
//                completionService.submit(new Callable<HashMap<String, Object>>() {
//                    @Override
//                    public HashMap<String, Object> call() throws Exception {
//                        try{
//                            String path = item.getImgUrl().substring(item.getImgUrl().lastIndexOf('/')+1);
//                            System.out.println("Strat download " + path);
//                            InputStream in = httpGetImg(client, item,filmDesc);
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                        return null;
//                    }
//                });
//            }
//            for(ImgDesc item:filmDesc.getImages()){
//                HashMap<String, Object> hashMap = completionService.take().get();
//            }
//        }catch (Exception e){
//
//        }finally {
//            cachedThreadPool.shutdownNow();
//        }
//
//    }
//    /**
//     * 发送get请求,  下载图片
//     * @param client
//     * @param imgDesc
//     * @return
//     */
//    public static InputStream httpGetImg(CloseableHttpClient client,ImgDesc imgDesc,FilmDesc filmDesc) {
//        // 发送get请求
//        HttpGet request = new HttpGet(imgDesc.getImgUrl());
//        // 设置请求和传输超时时间
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
//        //设置请求头
//        request.setHeader( "User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1" );
//        request.setHeader( "Connection","Keep-Alive" );
//        request.setHeader( "Content-Length","0" );
//        request.setHeader( "Host","www.yuoimg.com" );
//        request.setHeader( "Pragma","no-cache" );
//        request.setConfig(requestConfig);
//        try {
//            CloseableHttpResponse response = client.execute(request);
//            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
//                HttpEntity entity = response.getEntity();
//                InputStream in = entity.getContent();
//                imgDesc.setImgSize(in.available());
//                String path = filmDesc.getFilmName().trim()+"\\"+imgDesc.getImgUrl().substring(imgDesc.getImgUrl().lastIndexOf('/')+1);
//                FileUtils.copyInputStreamToFile(in, new File("E:\\Program Files\\1024Img\\"+path));
//                imgDesc.setImgMd5(DigestUtils.md5Hex(in));
//                imgDesc.setImgLocalPath(path);
//                System.out.println("下载图片成功:"+path);
//                //imgDesc.setImgMd5(DigestUtils.md5Hex(new FileInputStream(new File("E:\\Program Files\\1024Img\\"+filmDesc.getFilmName()+"\\"+path))));
//                return in;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            request.releaseConnection();
//        }
//        return null;
//    }
//    @Test
//    public void getPostsList() throws IOException {
//        String uri = "http://yj1.ceea3a8ea1f.rocks/pw/";
//        for(int page=57;page>0;page--){
//            String Url = String.format(uri+"simple/index.php?f90_%s.html",page);
//            List<Posts> posts = getPostsByUrl(Url);
//            for (Posts item:posts) {
//                List<FilmDesc> postscontent = getPostscontent(uri+item.getPostsurl());
//                System.out.println("");
//                if(!postsRepos.existsById(item.getPostsid())){
//                    postsRepos.save(item);
//                    for (FilmDesc filmDesc:postscontent){
//                        filmDescRepos.saveAndFlush(filmDesc);
//                    }
//                }
//            }
//
//        }
//    }
//
//
//    private List<Posts> getPostsByUrl(String postsUrl) throws IOException {
//        List<Posts> result = new ArrayList<Posts>();
//        Connection connect = Jsoup.connect(postsUrl);
//        Map<String, String> header = new HashMap<String, String>();
//        header.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; InfoPath.1; SV1; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E; .NET CLR 1.1.4322)");
//        header.put("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*");
//        header.put("Accept-Encoding", "gzip, deflate");
//        header.put("Accept-Language", "zh-CN");
//        header.put("Connection", "keep-alive");
//        header.put("Host", "s1.pbnmdcb.xyz");
//        Connection data = connect.headers(header);
//        Document document = connect.get();
//        Elements start = document.body().getElementsByAttribute("start");
//        if(start.size()>0){
//            for (Element element : start.get(0).getElementsByTag("li")) {
//                String categroyName = element.text();
//                Element el = element.select("a").first();
//                String url = el != null ? el.attr("href") : "";
//
//                Pattern p2=Pattern.compile("(\\d+)");
//                Matcher m2=p2.matcher(url);
//                String postsId = "";
//                if(m2.find()){
//                    postsId = m2.group();
//                }
//                Posts posts = new Posts(Integer.parseInt(postsId), "赛亚专区", url, categroyName.replace("(0 回复)", ""), new Timestamp(System.currentTimeMillis()));
//                result.add(posts);
//            }
//        }
//        return result;
//    }
//    public  List<FilmDesc> getPostscontent(String postItemUrl) throws IOException {
//        List<FilmDesc> filmDescs = new ArrayList<FilmDesc>();
//        Map<String, String> header = new HashMap<String, String>();
//        header.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; InfoPath.1; SV1; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E; .NET CLR 1.1.4322)");
//        header.put("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*");
//        header.put("Accept-Encoding", "gzip, deflate");
//        header.put("Accept-Language", "zh-CN");
//        header.put("Connection", "keep-alive");
//        header.put("Host", "s1.xp3839.rocks");
//        Document document = Jsoup.connect(postItemUrl).headers(header).get();
//        Elements tpc_content = document.body().getElementsByClass("tpc_content");
//        if(tpc_content.size()>0){
//            Pattern p2=Pattern.compile("【[\\u4e00-\\u9fa5]+】.*");
//            String contentText = tpc_content.text().replaceAll("【", "\n【");
//            if(!contentText.contains("【下载地址】")){
//                contentText = contentText.replaceAll("下载地址","\n【下载地址】" );
//            }
//            Matcher m2=p2.matcher(contentText);
//
//
//            FilmDesc filmDesc = new FilmDesc();
//            while (m2.find()) {
//                System.out.println(m2.group());
//                if(m2.group().contains("影片名称") || m2.group().contains("百度云泄露") ){
//                    filmDesc.setFilmName(m2.group().replaceAll("(?:【影片名称】:|【影片名称】：|【百度云泄露】:|【百度云泄露】：)", ""));
//                }else if(m2.group().contains("影片大小")){
//                    filmDesc.setFilmSize(m2.group().replaceAll("(?:【影片大小】:|【影片大小】：)", ""));
//                }else if(m2.group().contains("影片时间") || m2.group().contains("影片时长") ){
//                    filmDesc.setFilmDuration(m2.group().replaceAll("(?:【影片时间】:|【影片时间】：|【影片时长】:|【影片时长】：)", ""));
//                }else if(m2.group().contains("影片格式")){
//                    filmDesc.setFilmFormat(m2.group().replaceAll("(?:【影片格式】:|【影片格式】：)", ""));
//                }else if(m2.group().contains("是否有码")){
//                    filmDesc.setFilmCode(StringUtils.containsAny(m2.group().replaceAll("(?:【是否有码】:|【是否有码】：|是否有码)","" ),"无")?false:true);
//                }else if(m2.group().contains("影片背景") || m2.group().contains("视频背景")){
//                    filmDesc.setFilmBackground(m2.group().replaceAll("(?:【影片背景】:|【影片背景】：|【视频背景】:|【视频背景】：)", ""));
//                }else if(m2.group().contains("下载地址")){
//                    filmDescs.add(filmDesc);
//                    filmDesc = new FilmDesc();
//                }else{
//                    System.out.println(m2.group());
//                }
//            };
//            if(filmDescs!=null && filmDescs.size()>0){
//                int filmDescIndex=0;
//                Elements a = tpc_content.get(0).getElementsByTag("a");
//                List<ImgDesc> imgDescs = new ArrayList<ImgDesc>();
//                for (Element item:a){
//                    String url = item != null ? item.attr("href") : "";
//                    if(StringUtils.containsAny(item.text(),"点击进入","zip","下载")){
//                        filmDescs.get(filmDescIndex).setImages(imgDescs);
//                        filmDescs.get(filmDescIndex).setDownSrc(url);
//                        //filmDescs.get(filmDescIndex).setFilmId(autoId());
//                        /*for (ImgDesc imgDesc :filmDescs.get(filmDescIndex).getImages()) {
//                            httpGetImg(client,imgDesc,filmDesc);
//                        }*/
//                        //downImgThread(filmDescs.get(filmDescIndex));
//                        filmDescIndex++;
//                        imgDescs = new ArrayList<ImgDesc>();;
//                    }else{
//                        //获取src
//                        Elements img = item.getElementsByTag("img");
//                        if(img.size()>0){
//                            url = item != null ? img.get(0).attr("src") : "";
//                            ImgDesc imgDesc = new ImgDesc(url);
//                            imgDesc.setDownSrc(filmDesc);
//                            imgDescs.add(imgDesc);
//                        };
//                    }
//                }
//            }else{
//                Elements a = tpc_content.get(0).getElementsByTag("a");
//                List<ImgDesc> imgDescs = new ArrayList<ImgDesc>();
//                for (Element item:a){
//
//                    String url = item != null ? item.attr("href") : "";
//                    if(StringUtils.containsAny(item.text(),"点击进入","zip","下载")){
//                        filmDesc.setImages(imgDescs);
//                        filmDesc.setDownSrc(url);
//                        //filmDesc.setFilmId(autoId());
//                        filmDescs.add(filmDesc);
//                        //downImgThread(filmDesc);
//                       // filmDescRepos.saveAndFlush(filmDesc);
//                        imgDescs = new ArrayList<ImgDesc>();
//                        filmDesc = new FilmDesc();
//                    }else{
//                        //获取src
//                        Elements img = item.getElementsByTag("img");
//                        if(img.size()>0){
//                            url = item != null ? img.get(0).attr("src") : "";
//                            ImgDesc imgDesc = new ImgDesc(url);
//                            imgDesc.setDownSrc(filmDesc);
//                            imgDescs.add(imgDesc);
//                        };
//                    }
//                }
//            }
//        }
//        return filmDescs;
//    }
//    private Long autoId(){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        String batchnoNew = dateFormat.format(new Date())+ +RandomUtils.nextInt(0,99999);
//        return Long.parseLong(batchnoNew);
//    }
}
