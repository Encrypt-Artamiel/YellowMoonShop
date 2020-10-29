/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import org.apache.log4j.Logger;

/**
 * Web application lifecycle listener.
 *
 * @author DELL
 */
public class ServletContextListener implements javax.servlet.ServletContextListener {

    private static Map<String, String> siteMap;
    private static final Logger LOG = Logger.getLogger(ServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String path = context.getRealPath("/WEB-INF/SiteMap.txt");
        try {
            if (this.siteMap == null) {
                this.siteMap = readSiteMap(path);
            }
        } catch (IOException ex) {
            LOG.error("IOException : " + ex.getMessage());
        }
        context.setAttribute("MAP", this.siteMap);
    }

    private static Map<String, String> readSiteMap(String path) throws IOException {
        File file = null;
        FileReader fr = null;
        BufferedReader bf = null;
        Map<String, String> map = new HashMap<>();
        try {
            file = new File(path);
            fr = new FileReader(file);
            bf = new BufferedReader(fr);
            while (bf.ready()) {
                String[] line = bf.readLine().split("=");
                map.put(line[0], line[1]);
            }
        } finally {
            if (bf != null) {
                bf.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
        return map;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
