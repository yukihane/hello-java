// 参考: https://ja.vite.dev/config/
import type { UserConfig } from "vite";
import { resolve } from "path";
import { promises as fs } from "fs";

export default {
  build: {
    minify: false,
    // Vite の生成物を Spring Boot の static リソースディレクトリに出力する
    // "Vite の生成物" にはstaticリソース以外(thymeleafテンプレート(.htmlファイル))も含まれるが、それは後で処理する
    outDir: resolve(__dirname, "../src/main/resources/static"),
    // Viteの生成物はバージョン管理対象外にしたいので、それと分かるようディレクトリを分ける
    assetsDir: "vite",
    rollupOptions: {
      // resouces/templates に入れる html ファイルを列挙する
      input: {
        index: resolve(__dirname, "templates/index.html"),
      },
    },
  },
  plugins: [
    {
      // 全部outDir(resources/static)に入ってしまうので、templatesはresources/templatesに移動する
      name: "move-templates",
      closeBundle: async () => {
        const srcDir = resolve(
          __dirname,
          "../src/main/resources/static/templates"
        );
        const destDir = resolve(__dirname, "../src/main/resources/templates");

        try {
          await fs.mkdir(destDir, { recursive: true });
          await fs.cp(srcDir, destDir, { recursive: true, force: true });
          await fs.rm(srcDir, { recursive: true, force: true });
          console.log(`Moved templates from ${srcDir} to ${destDir}`);
        } catch (err) {
          console.error(`Failed to move templates: ${err}`);
        }
      },
    },
  ],
} satisfies UserConfig;
