import UIKit
import shared
import SDWebImage

class ViewController: UIViewController {
    
    /*
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return (images.data?.count)!
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> GiphyCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "giphyCell", for: indexPath)
        images.data?.
        (cell as! GiphyCell).displayContent(image: images.data?[0])
    } */
    
    
    // MARK: Properties
    @IBOutlet weak var goodButton: UIButton!
    @IBOutlet weak var evilButton: UIButton!
    @IBOutlet weak var imageView: UIImageView!
    //private var images: GiphyApi.SearchResult!

    private func handler() -> (GiphyApi.SearchResult) -> KotlinUnit {
        return {
            (result: GiphyApi.SearchResult) -> KotlinUnit  in
            for d in result.data! {
                print("type: \(d.type) id: \(d.id) url: \(d.images.fixedWidthDownSampled.url)")
            }
            if (Thread.isMainThread) {
                print("main thread")
            }
            //self.imageView.sd_setImage(with: URL(string: result.data![0].images.fixedWidthDownSampled.url))
            let index = arc4random_uniform(UInt32(result.data!.count))
            self.imageView.loadGif(url: result.data![Int(index)].images.fixedWidthDownSampled.url)
            /* do {
                if let url = URL(string: result.data![0].images.fixedWidthStill.url) {
                    let data = try Data(contentsOf: url)
                    self.imageView.image = UIImage(data: data)
                }
            } catch {
                print(error)
            } */
            return KotlinUnit()
        }
    }
    
    private let callback: (GiphyApi.SearchResult) -> KotlinUnit = {
        (result: GiphyApi.SearchResult) -> KotlinUnit  in
        for d in result.data! {
            print("type: \(d.type) id: \(d.id) url: \(d.images.fixedWidthDownSampled.url)")
        }
        return KotlinUnit()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

    @IBAction func pressed(_ sender: UIButton) {
        let g = GiphyApi()
        g.good(callback: handler())
        g.callTest()
    }
    
    @IBAction func evilPressed(_ sender: UIButton) {
        GiphyApi().evil(callback: handler())
    }
}
